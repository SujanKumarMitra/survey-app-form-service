package com.github.mitrakumarsujan.formservice.dao;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.github.mitrakumarsujan.formmodel.exception.ApplicationException;
import com.github.mitrakumarsujan.formmodel.exception.FormNotFoundException;
import com.github.mitrakumarsujan.formmodel.exception.RestCommunicationException;
import com.github.mitrakumarsujan.formmodel.exception.ServerErrorException;
import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.util.GenericRestTemplateFacade;
import com.github.mitrakumarsujan.formmodel.util.URIBuilderUtils;
import com.github.mitrakumarsujan.formservice.configuration.ApplicationConfigurationProperties;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-27
 */
@Repository
public class FormDaoImpl implements FormDao {

	private static final String FORM_ENDPOINT = "/v1/form";

	@Autowired
	private GenericRestTemplateFacade restTemplate;

	@Autowired
	private ApplicationConfigurationProperties properties;

	@Autowired
	private URIBuilderUtils uriBuilderUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(FormDaoImpl.class);

	@Override
	public Form save(Form form) throws ApplicationException {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = uriBuilderUtil.getURI(baseUrl, FORM_ENDPOINT);

		LOGGER.info("requesting data-storage-service to save form '{}'", form.getId());

		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.postForEntity(uri, form, String.class);
		} catch (ResourceAccessException e) {
			throw new RestCommunicationException();
		} catch (Exception e) {
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
//	@Cacheable
	public Form find(String formId) throws ApplicationException {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = uriBuilderUtil.getURI(baseUrl, FORM_ENDPOINT, formId);

		LOGGER.info("requesting data-storage-service to get form '{}'", formId);

		Form form = null;
		try {
			form = restTemplate.getRestSuccessResponseData(uri, HttpMethod.GET, null, Form.class);
		} catch (ResourceAccessException e) {
			throw new RestCommunicationException();
		} catch (HttpClientErrorException.NotFound e) {
			throw new FormNotFoundException(formId);
		} catch (Exception e) {
			throw new ServerErrorException(e);
		}
		LOGGER.info("received form '{}' from data-storage-service", formId);
		return form;
	}

}
