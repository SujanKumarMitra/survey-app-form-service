package com.github.mitrakumarsujan.formservice.model.response;

import org.springframework.http.HttpStatus;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface RestResponse {

	HttpStatus getStatus();

	default int getCode() {
		return getStatus().value();
	}

	String getMessage();

}
