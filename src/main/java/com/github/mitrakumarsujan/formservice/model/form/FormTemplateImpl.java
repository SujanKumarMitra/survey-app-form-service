package com.github.mitrakumarsujan.formservice.model.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-24
 */
@Valid
public class FormTemplateImpl implements FormTemplate {

	private static final long serialVersionUID = -6401907225149501099L;

	@NotNull(message = "must contain at least one field present")
	@Size(min = 1)
	@Valid
	private List<FormField> fields;

	@SuppressWarnings("unused")
	private FormTemplateImpl() {
	}

	public FormTemplateImpl(List<FormField> fields) {
		this.fields = fields;
	}


	@Override
	public List<FormField> getFields() {
		return fields;
	}


	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FormTemplate [fields=");
		builder.append(fields);
		builder.append("]");
		return builder.toString();
	}

}
