package com.github.mitrakumarsujan.formservice.service.validation.validator;

import java.time.LocalDate;
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
public class DateFieldValidator implements PatternBasedFormFieldValidator<DateField> {

	@Override
	public boolean validate(DateField field, Response response) {

		String pattern = field.getPattern();
		String input = response.getAnswer();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

		try {
			LocalDate.parse(input, formatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

}
