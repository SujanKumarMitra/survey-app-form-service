package com.github.mitrakumarsujan.formservice.service.keygenerator;

import com.github.mitrakumarsujan.formmodel.model.form.Form;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface KeyGeneratorService {
	String generate(Form form);
}
