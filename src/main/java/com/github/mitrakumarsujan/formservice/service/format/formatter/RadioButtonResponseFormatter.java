package com.github.mitrakumarsujan.formservice.service.format.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.RadioButtonField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.RadioButtonResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class RadioButtonResponseFormatter implements FormResponseFormatter<RadioButtonField, RadioButtonResponse> {

	@Autowired
	private DefaultSingleChoiceResponseFormatter delegatee;

	@Override
	public void format(RadioButtonField field, RadioButtonResponse response) {
		delegatee.format(field, response);
	}

}
