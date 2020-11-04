package com.github.mitrakumarsujan.formservice.service.format.formatter;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
public interface FormResponseFormatter<F extends FormField,R extends Response> {
	
	void format(F field, R response);

}
