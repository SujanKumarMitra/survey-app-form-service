package com.github.mitrakumarsujan.formservice.service.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.DateField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class TimeFieldValidator implements PatternBasedFormFieldValidator<DateField> {

	@Autowired
	private DefaultPatternBasedFormFieldValidator delegetee;
	
	@Override
	public boolean validate(DateField field, Response response) {
		return delegetee.validate(field, response);
	}

}
