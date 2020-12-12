package com.github.mitrakumarsujan.formservice.service.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TimeField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class TimeFieldValidator implements PatternBasedFormFieldValidator<TimeField> {
	
	@Autowired
	private DefaultPatternBasedFormFieldValidator delegatee;

	@Override
	public boolean validate(TimeField field, Response response) {
		return delegatee.validate(field, response);
	}
	
	@Override
	public String getErrorMessage() {
		return "Invalid time pattern";
	}

}
