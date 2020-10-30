package com.github.mitrakumarsujan.formservice.service.validation.validator;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.ChoiceBasedResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-30
 */
public interface ChoiceBasedFormFieldValidator<F extends FormField, R extends ChoiceBasedResponse>
		extends FormFieldValidator<F, R> {
	
	void formatResponse(F field, R response);
}