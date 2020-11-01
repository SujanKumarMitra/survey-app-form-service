package com.github.mitrakumarsujan.formservice.dao;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

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
	public Form save(Form form) {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = uriBuilderUtil.getURI(baseUrl, FORM_ENDPOINT);
		
		LOGGER.info("requesting data-storage-service to save form '{}'", form.getId());
		
		Form responseForm = restTemplate.getRestSuccessResponseData(uri, HttpMethod.POST, form, Form.class);
		
		LOGGER.info("data-storage-service saved form '{}'", form.getId());
		
		return responseForm;
	}

	@Override
//	@Cacheable
	public Form find(String formId) {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = uriBuilderUtil.getURI(baseUrl, FORM_ENDPOINT, formId);

		LOGGER.info("requesting data-storage-service to get form '{}'", formId);
		
		Form form = restTemplate.getRestSuccessResponseData(uri, HttpMethod.GET, null, Form.class);
		
		LOGGER.info("received form '{}' from data-storage-service", formId);
		return form;
	}

}
