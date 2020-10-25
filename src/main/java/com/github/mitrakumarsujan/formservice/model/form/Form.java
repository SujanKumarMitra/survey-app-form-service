package com.github.mitrakumarsujan.formservice.model.form;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface Form {

	String getUID();

	String getKey();

	FormTemplate getTemplate();
}
