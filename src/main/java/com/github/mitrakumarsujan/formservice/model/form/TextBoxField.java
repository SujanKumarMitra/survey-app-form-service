package com.github.mitrakumarsujan.formservice.model.form;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-24
 */
@Valid
@JsonPropertyOrder({"uid", "type", "question", "required" })
public class TextBoxField extends AbstractFormElement implements FormField {

	private static final long serialVersionUID = -3983594967960393598L;

	private TextBoxField() {
		super(null, false);
	}

	public TextBoxField(String question, boolean required) {
		super(question, required);
	}

	@Override
	public String getType() {
		return "textbox";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TextBoxField [question=");
		builder.append(getQuestion());
		builder.append(", required=");
		builder.append(isRequired());
		builder.append("]");
		return builder.toString();
	}

}
