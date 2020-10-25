package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formservice.model.form.TimeField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class TimeFieldUIDGenerator implements UIDGenerator<TimeField> {

	@Autowired
	private PatternFormFieldUIDGenerator delegator;

	@Override
	public String generate(TimeField timeField) {
		return delegator.generate(timeField);
	}

}
