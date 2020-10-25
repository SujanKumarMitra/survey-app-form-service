package com.github.mitrakumarsujan.formservice.model.form;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-24
 */
@JsonDeserialize(as = FormTemplateImpl.class)
public interface FormTemplate extends Serializable {

	List<FormField> getFields();
}
