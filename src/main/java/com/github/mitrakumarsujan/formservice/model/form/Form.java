package com.github.mitrakumarsujan.formservice.model.form;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@JsonPropertyOrder({ "uid", "key", "template" })
public interface Form extends Serializable {

	String getUID();

	String getKey();

	FormTemplate getTemplate();
}
