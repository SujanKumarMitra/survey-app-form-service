package com.github.mitrakumarsujan.formservice.service.validation;

import com.github.mitrakumarsujan.formservice.service.FormResponseRequest;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
public interface FormResponseValidationService {

	ValidationResult validate(FormResponseRequest request);
	
}