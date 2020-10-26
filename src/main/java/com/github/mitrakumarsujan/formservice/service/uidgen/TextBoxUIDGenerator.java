package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TextBoxField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class TextBoxUIDGenerator implements UIDGenerator<TextBoxField> {

	@Autowired
	private FormFieldUIDGenerator delegator;

	@Override
	public String generate(TextBoxField textBox) {
		return delegator.generate(textBox);
	}

}
