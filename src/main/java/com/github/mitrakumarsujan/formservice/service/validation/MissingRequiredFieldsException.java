package com.github.mitrakumarsujan.formservice.service.validation;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-03
 */
public class MissingRequiredFieldsException extends FormResponseValidationException {

	private static final long serialVersionUID = -5455625156414877965L;

	public MissingRequiredFieldsException(ValidationResult result) {
		super(result);
	}

}
