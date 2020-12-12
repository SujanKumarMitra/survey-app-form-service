package com.github.mitrakumarsujan.formservice.service.validation;

import java.util.Collection;

import com.github.mitrakumarsujan.formmodel.model.restresponse.error.ErrorInfo;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-03
 */
public interface ValidationResult {

	boolean hasErrors();

	String getMessage();

	Collection<ErrorInfo> getErrors();
}
