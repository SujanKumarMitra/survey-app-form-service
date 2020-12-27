package com.github.mitrakumarsujan.formservice.service.idgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.RadioField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class RadioIdGenerator implements IdGenerator<RadioField> {

	@Autowired
	private ChoiceBasedFormFieldIdGenerator delegatee;

	@Override
	public String generate(RadioField radioButton) {
		return delegatee.generate(radioButton);
	}

}
