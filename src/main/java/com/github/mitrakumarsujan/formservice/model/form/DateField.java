package com.github.mitrakumarsujan.formservice.model.form;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-24
 */
@JsonPropertyOrder({"uid", "type", "question", "required", "pattern" })
public class DateField extends AbstractFormElement implements PatternedFormField {

	private static final long serialVersionUID = 2450510899943300177L;

	private String pattern;

	private DateField() {
		super(null, false);
	}

	public DateField(String question, boolean required, String pattern) {
		super(question, required);
		this.pattern = pattern;
	}

	@Override
	public String getType() {
		return "date";
	}

	@Override
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		if (pattern == null)
			this.pattern = "yyyy-MM-dd";
		else
			this.pattern = pattern;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DateField [question=");
		builder.append(getQuestion());
		builder.append(", pattern=");
		builder.append(getPattern());
		builder.append(", required=");
		builder.append(isRequired());
		builder.append("]");
		return builder.toString();
	}

}
