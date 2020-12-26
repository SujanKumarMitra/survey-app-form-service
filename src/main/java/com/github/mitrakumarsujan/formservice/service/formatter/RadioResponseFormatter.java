package com.github.mitrakumarsujan.formservice.service.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.RadioField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.RadioResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class RadioResponseFormatter implements FormResponseFormatter<RadioField, RadioResponse> {

	@Autowired
	private DefaultSingleChoiceResponseFormatter delegatee;

	@Override
	public void format(RadioField field, RadioResponse response) {
		delegatee.format(field, response);
	}

}
