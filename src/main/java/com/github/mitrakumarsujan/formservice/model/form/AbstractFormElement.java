package com.github.mitrakumarsujan.formservice.model.form;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-24
 */
@Valid
public abstract class AbstractFormElement implements FormField {

	private static final long serialVersionUID = 5980074893003836601L;

	@NotNull(message = "question cannot be null")
	@NotEmpty(message = "question cannot be empty")
	@NotBlank(message = "question cannot be blank")
	private String question;
	private boolean required;

	@SuppressWarnings("unused")
	private AbstractFormElement() {
	}

	public AbstractFormElement(String question, boolean required) {
		this.required = required;
		this.question = question;
	}

	@Override
	public boolean isRequired() {
		return required;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
