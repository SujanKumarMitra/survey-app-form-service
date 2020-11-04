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
public interface FormResponseRequest {
	
	FormResponse getResponse();
	
	Form getAssociatedForm();
	
	Map<String, FormField> getFieldMap();
	
	Map<String, Response> getResponseMap();
}
