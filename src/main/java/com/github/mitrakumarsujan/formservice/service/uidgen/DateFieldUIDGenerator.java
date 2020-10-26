package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.DateField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class DateFieldUIDGenerator implements UIDGenerator<DateField> {

	@Autowired
	private PatternedFormFieldUIDGenerator delegatee;

	@Override
	public String generate(DateField dateField) {
		return delegatee.generate(dateField);
	}

}
