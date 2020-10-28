package com.github.mitrakumarsujan.formservice.service.validation.validator;

import java.util.function.Supplier;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formservice.service.validation.Validator;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
public interface FormFieldValidator<F extends FormField, R extends Response> extends Validator<F, R> {
	default Supplier<String> getErrorMessage() {
		return () -> "Invalid Response";
	}
}