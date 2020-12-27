package com.github.mitrakumarsujan.formservice.service.idgenerator;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.form.OptionField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface IdGeneratorService {
	String generate(Form form);

	String generate(FormField formField);

	String generate(OptionField optionField);
}
