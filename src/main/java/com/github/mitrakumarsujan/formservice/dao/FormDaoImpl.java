package com.github.mitrakumarsujan.formservice.dao;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.response.success.MutableRestSuccessResponse;
import com.github.mitrakumarsujan.formservice.configuration.ApplicationConfigurationProperties;
import com.github.mitrakumarsujan.formservice.util.GenericRestTemplateFacade;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-27
 */
@Service
public class FormDaoImpl implements FormDao {

	@Autowired
	private GenericRestTemplateFacade restTemplate;

	@Autowired
	private ApplicationConfigurationProperties properties;

	@Override
	public Form save(Form form) {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = getURI(baseUrl, "/v1/form");

		Form responseForm = getResponse(uri, HttpMethod.POST, form);
		return responseForm;
	}

	@Override
//	@Cacheable
	public Form find(String formUID) {
		String baseUrl = properties.getDataStorageServiceUrl();
		URI uri = getURI(baseUrl, "/v1/form/" + formUID);

		Form form = getResponse(uri, HttpMethod.GET, null);
		return form;
	}

	private URI getURI(String baseUrl, String path) {
		return UriComponentsBuilder	.fromHttpUrl(baseUrl)
									.path(path)
									.build()
									.toUri();
	}

	private Form getResponse(URI uri, HttpMethod httpMethod, Form form) {

		ResponseEntity<MutableRestSuccessResponse<Form>> exchange = restTemplate.exchange(uri, httpMethod,
				new HttpEntity<>(form), MutableRestSuccessResponse.class, Form.class);

		return exchange	.getBody()
						.getData();

	}

}
