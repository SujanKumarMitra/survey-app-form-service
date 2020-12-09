package com.github.mitrakumarsujan.formservice.dao;

import com.github.mitrakumarsujan.formmodel.exception.ApplicationException;
import com.github.mitrakumarsujan.formmodel.exception.RestCommunicationException;
import com.github.mitrakumarsujan.formmodel.exception.ServerErrorException;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.util.URIBuilderUtils;
import com.github.mitrakumarsujan.formservice.configuration.ServiceEndpointsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-31
 */
@Repository
@RefreshScope
public class FormResponseDaoImpl implements FormResponseDao {

	private static final String FORM_RESPONSE_PATH = "v1/formResponse";

	@Autowired
	@Qualifier("load-balanced-rest-template")
	private RestTemplate restTemplate;

	@Autowired
	private ServiceEndpointsConfiguration serviceEndpointsConfig;

	@Autowired
	private URIBuilderUtils uriBuilderUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(FormResponseDaoImpl.class);

	@Override
	public FormResponse save(FormResponse response) throws ApplicationException {
		String baseUrl = serviceEndpointsConfig.getDataStorageServiceEndpoint();
		URI uri = uriBuilderUtil.getURI(baseUrl, FORM_RESPONSE_PATH);

		String formId = response.getFormId();
		LOGGER.info("requesting data-storage-service to save formResponse for formId '{}'", formId);

		ResponseEntity<String> responseEntity;
		try {
			responseEntity = restTemplate.postForEntity(uri, response, String.class);
		} catch (ResourceAccessException e) {
			throw new RestCommunicationException();
		} catch (Exception e) {
			throw new ServerErrorException(e);
		}

		HttpStatus statusCode = responseEntity.getStatusCode();
		if (statusCode != HttpStatus.CREATED) {
			throw new ServerErrorException(responseEntity.getBody());
		}

		LOGGER.info("data-storage-service saved formResponse for formId '{}'", formId);
		return response;
	}

}
