package com.github.mitrakumarsujan.formservice.service.formatter;

import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class NoOpFormatter implements FormResponseFormatter<FormField, Response> {

	@Override
	public void format(FormField field, Response response) {}

}
