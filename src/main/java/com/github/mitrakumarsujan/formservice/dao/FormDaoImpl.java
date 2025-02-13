package com.github.mitrakumarsujan.formservice.dao;

import com.github.mitrakumarsujan.formmodel.exception.ApplicationException;
import com.github.mitrakumarsujan.formmodel.exception.RestCommunicationException;
import com.github.mitrakumarsujan.formmodel.exception.ServerErrorException;
import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.restresponse.RestSuccessResponse;
import com.github.mitrakumarsujan.formmodel.util.URIBuilderUtils;
import com.github.mitrakumarsujan.formservice.configuration.ServiceEndpointsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-27
 */
@Service
@RefreshScope
public class FormDaoImpl implements FormDao {

    private static final String FORM_PATH = "v1/form";
    private static final Logger LOGGER = LoggerFactory.getLogger(FormDaoImpl.class);
    @Autowired
    @Qualifier("load-balanced-rest-template")
    private RestTemplate restTemplate;
    @Autowired
    private ServiceEndpointsConfiguration serviceEndpointsConfig;
    @Autowired
    private URIBuilderUtils uriBuilderUtil;

    @Override
    public Form save(Form form) throws ApplicationException {
        String baseUrl = serviceEndpointsConfig.getDataStorageServiceEndpoint();
        URI uri = uriBuilderUtil.getURI(baseUrl, FORM_PATH);

        LOGGER.info("requesting data-storage-service to save form '{}'", form.getId());

        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity(uri, form, String.class);
        } catch (ResourceAccessException e) {
            LOGGER.warn(e.toString());
            throw new RestCommunicationException();
        } catch (Exception e) {
            LOGGER.warn(e.toString());
            throw new ServerErrorException(e);
        }
        HttpStatus status = responseEntity.getStatusCode();
        if (status != HttpStatus.CREATED) {
            throw new ServerErrorException("form not created");
        }
        LOGGER.info("data-storage-service saved form '{}'", form.getId());
        return form;

    }

    @Override
    public Optional<Form> find(String formId) throws ApplicationException {
        String baseUrl = serviceEndpointsConfig.getDataStorageServiceEndpoint();
        URI uri = uriBuilderUtil.getURI(baseUrl, FORM_PATH, formId);

        LOGGER.info("requesting data-storage-service to get form '{}'", formId);
        Form form = null;
        try {
            ResponseEntity<RestSuccessResponse<Form>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<RestSuccessResponse<Form>>() {
                    });
            form = response.getBody().getData();
            LOGGER.info("received form '{}' from data-storage-service", formId);
            return Optional.of(form);
        } catch (ResourceAccessException e) {
            LOGGER.warn(e.toString());
            throw new RestCommunicationException();
        } catch (HttpClientErrorException.NotFound e) {
            LOGGER.warn(e.toString());
            return Optional.empty();
        } catch (Exception e) {
            LOGGER.warn(e.toString());
            throw new ServerErrorException(e);
        }
    }

}
