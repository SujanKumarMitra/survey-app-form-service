package com.github.mitrakumarsujan.formservice.service.format.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TimeField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.TimeResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class TimeResponseFormatter implements FormResponseFormatter<TimeField, TimeResponse> {

	@Autowired
	private NoOpFormatter delegatee;

	@Override
	public void format(TimeField field, TimeResponse response) {
		delegatee.format(field, response);
	}

}
