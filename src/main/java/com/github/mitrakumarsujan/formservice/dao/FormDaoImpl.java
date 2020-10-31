package com.github.mitrakumarsujan.formservice.dao;

import java.net.URI;

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

	@Override
	public Form save(Form form) {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = uriBuilderUtil.getURI(baseUrl, FORM_ENDPOINT);

		Form responseForm = restTemplate.getRestSuccessResponseData(uri, HttpMethod.POST, form, Form.class);
		return responseForm;
	}

	@Override
//	@Cacheable
	public Form find(String formId) {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = uriBuilderUtil.getURI(baseUrl, FORM_ENDPOINT, formId);

		Form form = restTemplate.getRestSuccessResponseData(uri, HttpMethod.GET, null, Form.class);
		return form;
	}

}
