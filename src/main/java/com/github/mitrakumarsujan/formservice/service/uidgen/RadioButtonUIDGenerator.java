package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.RadioButtonField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class RadioButtonUIDGenerator implements UIDGenerator<RadioButtonField> {

	@Autowired
	private ChoiceTypeFormFieldUIDGenerator delegatee;

	@Override
	public String generate(RadioButtonField radioButton) {
		return delegatee.generate(radioButton);
	}

}
