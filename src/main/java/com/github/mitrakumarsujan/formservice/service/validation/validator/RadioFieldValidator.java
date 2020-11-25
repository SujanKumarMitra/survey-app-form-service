package com.github.mitrakumarsujan.formservice.service.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.RadioField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.SingleChoiceBasedResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class RadioFieldValidator implements SingleChoiceBasedFormFieldValidator<RadioField> {

	@Autowired
	private DefaultSingleChoiceBasedFormFieldValidator delegatee;

	@Override
	public boolean validate(RadioField field, SingleChoiceBasedResponse response) {
		return delegatee.validate(field, response);
	}

	@Override
	public String getErrorMessage() {
		return "option id not matching";
	}

}
