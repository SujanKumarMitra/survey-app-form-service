package com.github.mitrakumarsujan.formservice.service.validation.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.PatternBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class DefaultPatternBasedFormFieldValidator implements PatternBasedFormFieldValidator<PatternBasedFormField> {

	@Override
	public boolean validate(PatternBasedFormField field, Response response) {
		String pattern = field.getPattern();
		String input = response.getAnswer();
		return Pattern.matches(pattern, input);
	}

}
