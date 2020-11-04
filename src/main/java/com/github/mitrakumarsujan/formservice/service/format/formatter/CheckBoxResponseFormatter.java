package com.github.mitrakumarsujan.formservice.service.format.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.CheckBoxField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.CheckBoxResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class CheckBoxResponseFormatter implements FormResponseFormatter<CheckBoxField, CheckBoxResponse> {

	@Autowired
	private DefaultMultipleChoiceResponseFormatter delegatee;
	
	@Override
	public void format(CheckBoxField field, CheckBoxResponse response) {
		delegatee.format(field, response);
	}

}
