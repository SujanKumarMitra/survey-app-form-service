package com.github.mitrakumarsujan.formservice.dao;

import com.github.mitrakumarsujan.formmodel.model.form.Form;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-27
 */
public interface FormDao {
	
	Form save(Form form);
	
	Form find(String formId);
	
}
