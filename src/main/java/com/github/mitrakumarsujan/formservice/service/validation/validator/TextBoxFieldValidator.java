package com.github.mitrakumarsujan.formservice.service.validation.validator;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TextBoxField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class TextBoxFieldValidator implements FormFieldValidator<TextBoxField, Response> {

	@Override
	public boolean validate(TextBoxField field, Response response) {

		String answer = response.getAnswer();
		boolean required = field.isRequired();

		if (required) {
			return answer != null && !answer.isEmpty() && !answer.isBlank();
		} else {
			return true;
		}
	}

}
