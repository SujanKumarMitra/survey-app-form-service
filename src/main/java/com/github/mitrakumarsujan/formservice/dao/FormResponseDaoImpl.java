package com.github.mitrakumarsujan.formservice.dao;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.ResourceAccessException;

import com.github.mitrakumarsujan.formmodel.exception.ApplicationException;
import com.github.mitrakumarsujan.formmodel.exception.RestCommunicationException;
import com.github.mitrakumarsujan.formmodel.exception.ServerErrorException;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.util.GenericRestTemplateFacade;
import com.github.mitrakumarsujan.formmodel.util.URIBuilderUtils;
import com.github.mitrakumarsujan.formservice.configuration.ApplicationConfigurationProperties;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-31
 */
@Repository
public class FormResponseDaoImpl implements FormResponseDao {

	private static final String FORM_RESPONSE_ENDPOINT = "/v1/formResponse";

	@Autowired
	private GenericRestTemplateFacade restTemplate;

	@Autowired
	private ApplicationConfigurationProperties properties;

	@Autowired
	private URIBuilderUtils uriBuilderUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(FormResponseDaoImpl.class);

	@Override
	public FormResponse save(FormResponse response) throws ApplicationException {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = uriBuilderUtil.getURI(baseUrl, FORM_RESPONSE_ENDPOINT);

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

	@Override
	public List<FormResponse> getAll(String formId) {
		// TODO Auto-generated method stub
		return null;
	}

}
