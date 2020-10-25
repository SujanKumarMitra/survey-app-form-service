package com.github.mitrakumarsujan.formservice.model.form;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-24
 */
@Valid
@JsonPropertyOrder({"uid", "type", "question", "required", "pattern" })
public class TimeField extends AbstractFormElement implements PatternedFormField {

	private static final long serialVersionUID = -8875415602189929487L;

	private String pattern;

	private TimeField() {
		super(null, false);
	}

	public TimeField(String question, boolean required, String pattern) {
		super(question, required);
		this.pattern = pattern;
	}

	@Override
	public String getType() {
		return "time";
	}

	@Override
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		if (pattern == null)
			this.pattern = "HH:MM:SS";
		else
			this.pattern = pattern;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TimeField [pattern=");
		builder.append(pattern);
		builder.append(", required=");
		builder.append(isRequired());
		builder.append(", question=");
		builder.append(getQuestion());
		builder.append("]");
		return builder.toString();
	}

}
