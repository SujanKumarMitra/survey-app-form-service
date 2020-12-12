package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.RadioField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class RadioUIDGenerator implements UIDGenerator<RadioField> {

	@Autowired
	private ChoiceBasedFormFieldUIDGenerator delegatee;

	@Override
	public String generate(RadioField radioButton) {
		return delegatee.generate(radioButton);
	}

}
