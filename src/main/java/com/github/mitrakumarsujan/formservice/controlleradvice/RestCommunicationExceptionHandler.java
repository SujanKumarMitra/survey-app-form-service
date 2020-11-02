package com.github.mitrakumarsujan.formservice.controlleradvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.mitrakumarsujan.formmodel.exception.RestCommunicationException;
import com.github.mitrakumarsujan.formmodel.model.restresponse.RestErrorResponse;
import com.github.mitrakumarsujan.formmodel.model.restresponse.error.RestErrorResponseBuilderFactory;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-02
 */
@RestControllerAdvice
public class RestCommunicationExceptionHandler {
	
	@Autowired
	private RestErrorResponseBuilderFactory builderFactory;
	
	@ExceptionHandler(RestCommunicationException.class)
	public ResponseEntity<RestErrorResponse> handleFormNotFoundException(RestCommunicationException exception) {
		return builderFactory	.getErrorBuilder()
								.withStatus(HttpStatus.GATEWAY_TIMEOUT)
								.withMessage("Server is not responding")
								.withErrors(null)
								.build()
								.toResponseEntity();
	}
}
