package com.github.mitrakumarsujan.formservice.service.validation.validator;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author skmitra
 * @since 2020-11-12
 */
public interface FormFieldValidatorFactory {

	<F extends FormField> FormFieldValidator<F, Response> getValidator(Class<? extends F> fieldType);

	<F extends FormField, R extends Response> FormFieldValidator<F, R> getValidator(Class<? extends F> fieldType,
			Class<? extends R> responseType);

	<F extends FormField> void registerValidator(Class<? extends F> fieldType,
			FormFieldValidator<? extends F, ? extends Response> validator);

}