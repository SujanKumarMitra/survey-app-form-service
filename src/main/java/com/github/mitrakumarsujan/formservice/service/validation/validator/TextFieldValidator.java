package com.github.mitrakumarsujan.formservice.service.validation.validator;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TextField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class TextFieldValidator implements FormFieldValidator<TextField, Response> {

	@Override
	public boolean validate(TextField field, Response response) {

		String answer = response.getAnswer();
		boolean required = field.isRequired();

		if (required) {
			return answer != null && !answer.isEmpty() && !answer.isBlank();
		} else {
			return true;
		}
	}

	@Override
	public String getErrorMessage() {
		return "answer either empty or blank";
	}

}
