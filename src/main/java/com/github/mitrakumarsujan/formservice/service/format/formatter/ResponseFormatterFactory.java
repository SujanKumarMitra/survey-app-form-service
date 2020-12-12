package com.github.mitrakumarsujan.formservice.service.format.formatter;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author skmitra
 * @since 2020-11-12
 */
public interface ResponseFormatterFactory {

	<R extends Response> FormResponseFormatter<FormField, R> getFormatter(Class<? extends R> responseType);

	<F extends FormField, R extends Response> FormResponseFormatter<F, R> getFormatter(Class<? extends R> responseType,
			Class<? extends F> fieldType);

	<R extends Response> void registerFormatter(Class<? extends R> responseType,
			FormResponseFormatter<? extends FormField, R> formatter);

}