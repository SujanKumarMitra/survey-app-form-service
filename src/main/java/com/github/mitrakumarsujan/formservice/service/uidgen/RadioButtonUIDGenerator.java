package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formservice.model.form.RadioButtonField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class RadioButtonUIDGenerator implements UIDGenerator<RadioButtonField> {

	@Autowired
	private ChoiceTypeFormFieldUIDGenerator delegator;

	@Override
	public String generate(RadioButtonField radioButton) {
		return delegator.generate(radioButton);
	}

}
