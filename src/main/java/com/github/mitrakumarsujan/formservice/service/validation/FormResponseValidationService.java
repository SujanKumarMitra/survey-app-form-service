package com.github.mitrakumarsujan.formservice.service.validation;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
public interface FormResponseValidationService extends Validator<Form, FormResponse> {

	@Override
	boolean validate(Form form, FormResponse response);
	
}