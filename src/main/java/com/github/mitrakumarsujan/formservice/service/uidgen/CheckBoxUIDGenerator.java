package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.CheckBoxField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class CheckBoxUIDGenerator implements UIDGenerator<CheckBoxField> {

	@Autowired
	private ChoiceBasedFormFieldUIDGenerator delegatee;

	@Override
	public String generate(CheckBoxField checkBox) {
		return delegatee.generate(checkBox);
	}

}
