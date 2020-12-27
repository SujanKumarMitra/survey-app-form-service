package com.github.mitrakumarsujan.formservice.service.idgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.DateField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class DateFieldIdGenerator implements IdGenerator<DateField> {

	@Autowired
	private PatternBasedFormFieldIdGenerator delegatee;

	@Override
	public String generate(DateField dateField) {
		return delegatee.generate(dateField);
	}

}
