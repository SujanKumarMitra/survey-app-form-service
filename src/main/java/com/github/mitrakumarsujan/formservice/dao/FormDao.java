package com.github.mitrakumarsujan.formservice.dao;

import com.github.mitrakumarsujan.formmodel.exception.FormNotFoundException;
import com.github.mitrakumarsujan.formmodel.model.form.Form;

import java.util.Optional;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-27
 */
public interface FormDao {
	
	Form save(Form form);
	
	Optional<Form> find(String formId);
	
}
