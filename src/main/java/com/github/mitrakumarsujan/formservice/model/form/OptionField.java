package com.github.mitrakumarsujan.formservice.model.form;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-24
 */
@JsonDeserialize(as = OptionFieldImpl.class)
public interface OptionField extends Serializable {

	void setUID(String uid);

	String getUID();

	String getText();

}
