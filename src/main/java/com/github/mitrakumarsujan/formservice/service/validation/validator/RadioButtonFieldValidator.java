package com.github.mitrakumarsujan.formservice.service.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.RadioButtonField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.SingleChoiceBasedResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Component
public class RadioButtonFieldValidator implements SingleChoiceBasedFormFieldValidator<RadioButtonField> {

	@Autowired
	private DefaultSingleChoiceBasedFormFieldValidator delegetee;

	@Override
	public boolean validate(RadioButtonField field, SingleChoiceBasedResponse response) {
		return delegetee.validate(field, response);
	}


}
