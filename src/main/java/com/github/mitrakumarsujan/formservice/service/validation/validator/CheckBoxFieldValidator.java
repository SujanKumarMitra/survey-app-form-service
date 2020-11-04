package com.github.mitrakumarsujan.formservice.service.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.CheckBoxField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.MultipleChoiceBasedResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class CheckBoxFieldValidator implements MultipleChoiceBasedFormFieldValidator<CheckBoxField> {

	@Autowired
	private DefaultMultipleChoiceBasedFormFieldValidator delegatee;

	@Override
	public boolean validate(CheckBoxField field, MultipleChoiceBasedResponse response) {
		return delegatee.validate(field, response);
	}

	@Override
	public String getErrorMessage() {
		return "option id(s) not matching";
	}

}
