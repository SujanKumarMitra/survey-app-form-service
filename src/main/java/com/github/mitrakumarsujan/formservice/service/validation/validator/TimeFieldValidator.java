package com.github.mitrakumarsujan.formservice.service.validation.validator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.DateField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class TimeFieldValidator implements PatternBasedFormFieldValidator<DateField> {

	@Override
	public boolean validate(DateField field, Response response) {
		String pattern = field.getPattern();
		String input = response.getAnswer();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		
		try {
			LocalTime.parse(input, formatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

}
