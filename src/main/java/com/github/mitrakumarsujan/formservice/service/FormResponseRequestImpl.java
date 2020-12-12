package com.github.mitrakumarsujan.formservice.service;

import java.util.Map;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
public class FormResponseRequestImpl implements FormResponseRequest {
	
	private final FormResponse response;
	private final Form form;
	private final Map<String,FormField> fieldMap;
	private final Map<String,Response> responseMap;

	public FormResponseRequestImpl(FormResponse response, Form form, Map<String, FormField> fieldMap,
			Map<String, Response> responseMap) {
		this.response = response;
		this.form = form;
		this.fieldMap = fieldMap;
		this.responseMap = responseMap;
	}

	@Override
	public FormResponse getResponse() {
		return response;
	}

	@Override
	public Form getAssociatedForm() {
		return form;
	}

	@Override
	public Map<String, FormField> getFieldMap() {
		return fieldMap;
	}

	@Override
	public Map<String, Response> getResponseMap() {
		return responseMap;
	}

}
