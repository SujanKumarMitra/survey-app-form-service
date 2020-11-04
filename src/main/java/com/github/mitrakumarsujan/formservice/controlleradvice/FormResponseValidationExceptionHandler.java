package com.github.mitrakumarsujan.formservice.controlleradvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.mitrakumarsujan.formmodel.model.restresponse.RestErrorResponse;
import com.github.mitrakumarsujan.formmodel.model.restresponse.error.RestErrorResponseBuilderFactory;
import com.github.mitrakumarsujan.formservice.service.validation.FormResponseValidationException;
import com.github.mitrakumarsujan.formservice.service.validation.ValidationResult;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-02
 */
@RestControllerAdvice
public class FormResponseValidationExceptionHandler {

	@Autowired
	private RestErrorResponseBuilderFactory builderFactory;

	@ExceptionHandler(FormResponseValidationException.class)
	public ResponseEntity<RestErrorResponse> handle(FormResponseValidationException exception) {
		
		ValidationResult result  = exception.getResult();
		
		return builderFactory	.getErrorBuilder()
								.withStatus(HttpStatus.UNPROCESSABLE_ENTITY)
								.withErrors(result.getErrors())
								.withMessage(result.getMessage())
								.build()
								.toResponseEntity();
	}

}
