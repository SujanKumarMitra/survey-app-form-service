package com.github.mitrakumarsujan.formservice.service.validation;

import java.util.Collection;

import com.github.mitrakumarsujan.formmodel.model.restresponse.error.ErrorInfo;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-03
 */
public class ValidationResultImpl implements ValidationResult {

	private boolean errors;
	private String message;
	private Collection<ErrorInfo> errorInfos;

	public ValidationResultImpl(boolean errors, String message, Collection<ErrorInfo> errorInfos) {
		this.errors = errors;
		this.message = message;
		this.errorInfos = errorInfos;
	}

	@Override
	public boolean hasErrors() {
		return errors;
	}

	@Override
	public Collection<ErrorInfo> getErrors() {
		return errorInfos;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
