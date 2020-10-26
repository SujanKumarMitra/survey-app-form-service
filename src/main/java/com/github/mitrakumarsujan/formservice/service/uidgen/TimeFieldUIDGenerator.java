package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TimeField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class TimeFieldUIDGenerator implements UIDGenerator<TimeField> {

	@Autowired
	private PatternedFormFieldUIDGenerator delegatee;

	@Override
	public String generate(com.github.mitrakumarsujan.formmodel.model.form.TimeField timeField) {
		return delegatee.generate(timeField);
	}

}
