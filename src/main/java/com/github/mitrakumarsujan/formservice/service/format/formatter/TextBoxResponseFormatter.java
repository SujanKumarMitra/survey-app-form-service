package com.github.mitrakumarsujan.formservice.service.format.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TextBoxField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.TextBoxResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class TextBoxResponseFormatter implements FormResponseFormatter<TextBoxField, TextBoxResponse> {

	@Autowired
	private NoOpFormatter delegatee;

	@Override
	public void format(TextBoxField field, TextBoxResponse response) {
		delegatee.format(field, response);
	}

}
