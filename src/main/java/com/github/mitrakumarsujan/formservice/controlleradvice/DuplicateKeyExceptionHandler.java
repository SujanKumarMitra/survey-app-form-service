package com.github.mitrakumarsujan.formservice.controlleradvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.mitrakumarsujan.formmodel.exception.DuplicateKeyException;
import com.github.mitrakumarsujan.formmodel.model.restresponse.RestErrorResponse;
import com.github.mitrakumarsujan.formmodel.model.restresponse.error.RestErrorResponseBuilderFactory;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-02
 */
@RestControllerAdvice
public class DuplicateKeyExceptionHandler {

	@Autowired
	private RestErrorResponseBuilderFactory builderFactory;

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<RestErrorResponse> handle(DuplicateKeyException exception) {
		return builderFactory	.getErrorBuilder()
								.withStatus(HttpStatus.UNPROCESSABLE_ENTITY)
								.withMessage(exception.getLocalizedMessage())
								.build()
								.toResponseEntity();
	}

}
