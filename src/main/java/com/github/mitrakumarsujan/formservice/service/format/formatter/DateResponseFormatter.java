package com.github.mitrakumarsujan.formservice.service.format.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.DateField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.DateResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class DateResponseFormatter implements FormResponseFormatter<DateField, DateResponse> {
	
	@Autowired
	private NoOpFormatter delegatee;

	@Override
	public void format(DateField field, DateResponse response) {
		delegatee.format(field, response);
	}

}
