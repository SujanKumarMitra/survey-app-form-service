package com.github.mitrakumarsujan.formservice.service.validation;

import com.github.mitrakumarsujan.formmodel.exception.ClientErrorException;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-03
 */
public class FormResponseValidationException extends ClientErrorException {

	private static final long serialVersionUID = -6160313765906196840L;

	private final ValidationResult result;

	public FormResponseValidationException(ValidationResult result) {
		super(result.getErrors());
		this.result = result;
	}

	public ValidationResult getResult() {
		return result;
	}

}
