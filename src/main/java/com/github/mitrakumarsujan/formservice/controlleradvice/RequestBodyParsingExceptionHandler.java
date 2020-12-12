package com.github.mitrakumarsujan.formservice.controlleradvice;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.mitrakumarsujan.formmodel.model.restresponse.RestErrorResponse;
import com.github.mitrakumarsujan.formmodel.model.restresponse.error.ErrorInfoImpl;
import com.github.mitrakumarsujan.formmodel.model.restresponse.error.RestErrorResponseBuilderFactory;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-02
 */
@RestControllerAdvice
public class RequestBodyParsingExceptionHandler {

	@Autowired
	private RestErrorResponseBuilderFactory builderFactory;

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<RestErrorResponse> handle(HttpMessageNotReadableException exception) {
		return builderFactory	.getErrorBuilder()
								.withStatus(HttpStatus.UNPROCESSABLE_ENTITY)
								.withMessage("request body has errors.")
								.withErrors(Collections.singletonList(
										new ErrorInfoImpl(exception.getMessage(), exception)))
								.build()
								.toResponseEntity();
	}

}
