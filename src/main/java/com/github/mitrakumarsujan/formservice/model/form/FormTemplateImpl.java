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

	private String id;

	@NotNull(message = "must contain at least one field present")
	@Size(min = 1)
	@Valid
	private List<FormField> fields;

	@SuppressWarnings("unused")
	private FormTemplateImpl() {
		this.id = "$";
	}

	public FormTemplateImpl(String id, List<FormField> fields) {
		this.id = id;
		this.fields = fields;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public List<FormField> getFields() {
		return fields;
	}

	public void setId(String id) {
		if (id == null || id.equals(""))
			this.id = "$";
		else
			this.id = id;
	}

	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FormTemplate [id=");
		builder.append(id);
		builder.append(", fields=");
		builder.append(fields);
		builder.append("]");
		return builder.toString();
	}

}
