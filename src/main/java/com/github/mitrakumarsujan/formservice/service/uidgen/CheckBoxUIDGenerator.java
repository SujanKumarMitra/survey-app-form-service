package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formservice.model.form.CheckBoxField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class CheckBoxUIDGenerator implements UIDGenerator<CheckBoxField> {

	@Autowired
	private ChoiceTypeFormFieldUIDGenerator delegator;

	@Override
	public String generate(CheckBoxField checkBox) {
		return delegator.generate(checkBox);
	}

}
