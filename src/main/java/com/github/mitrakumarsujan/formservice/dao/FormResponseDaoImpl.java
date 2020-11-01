package com.github.mitrakumarsujan.formservice.dao;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

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
	public FormResponse save(FormResponse response) {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = uriBuilderUtil.getURI(baseUrl, FORM_RESPONSE_ENDPOINT);
		
		String formId = response.getFormId();
		LOGGER.info("requesting data-storage-service to save formResponse for formId '{}'", formId);
		
		FormResponse formResponse = restTemplate.getRestSuccessResponseData(uri, HttpMethod.POST, response,
				FormResponse.class);
		
		LOGGER.info("data-storage-service saved formResponse for formId '{}'", formId);
		
		return formResponse;
	}

	@Override
	public List<FormResponse> getAll(String formId) {
		// TODO Auto-generated method stub
		return null;
	}

}
