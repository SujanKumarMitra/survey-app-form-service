package com.github.mitrakumarsujan.formservice.model.form;

import java.io.Serializable;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface Form extends Serializable {

	String getUID();

	String getKey();

	FormTemplate getTemplate();
}
