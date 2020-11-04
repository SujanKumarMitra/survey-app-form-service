package com.github.mitrakumarsujan.formservice.controlleradvice;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.github.mitrakumarsujan.formmodel.exception.ServerErrorException;
import com.github.mitrakumarsujan.formmodel.model.restresponse.RestErrorResponse;
import com.github.mitrakumarsujan.formmodel.model.restresponse.error.ErrorInfoImpl;
import com.github.mitrakumarsujan.formmodel.model.restresponse.error.RestErrorResponseBuilderFactory;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-02
 */
@RestControllerAdvice
public class ServerErrorExceptionHandler {

	@Autowired
	private RestErrorResponseBuilderFactory builderFactory;

	@ExceptionHandler(ServerErrorException.class)
	public ResponseEntity<RestErrorResponse> handle(ServerErrorException exception) {

		String message = exception.getMessage();
		String className = message	.getClass()
									.getName();
		
		return builderFactory	.getErrorBuilder()
								.withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
								.withMessage(message)
								.withErrors(Collections.singleton(new ErrorInfoImpl(message, className)))
								.build()
								.toResponseEntity();
	}
}
