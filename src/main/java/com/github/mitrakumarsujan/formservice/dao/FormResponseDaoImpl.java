package com.github.mitrakumarsujan.formservice.dao;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.util.GenericRestTemplateFacade;
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

	@Override
	public FormResponse save(FormResponse response) {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = URI.create(baseUrl + FORM_RESPONSE_ENDPOINT);

		FormResponse formResponse = restTemplate.getRestSuccessResponseData(uri, HttpMethod.POST, response,
				FormResponse.class);
		return formResponse;
	}

	@Override
	public List<FormResponse> getAll(String formId) {
		// TODO Auto-generated method stub
		return null;
	}

}
