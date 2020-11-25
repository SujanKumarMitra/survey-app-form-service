package com.github.mitrakumarsujan.formservice.service.format.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TextField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.TextFieldResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class TextFieldResponseFormatter implements FormResponseFormatter<TextField, TextFieldResponse> {

	@Autowired
	private NoOpFormatter delegatee;

	@Override
	public void format(TextField field, TextFieldResponse response) {
		delegatee.format(field, response);
	}

}
