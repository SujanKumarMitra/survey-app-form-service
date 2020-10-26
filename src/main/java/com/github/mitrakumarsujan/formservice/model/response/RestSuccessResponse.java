package com.github.mitrakumarsujan.formservice.model.response;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@JsonPropertyOrder({ "status", "code", "message", "data" })
public interface RestSuccessResponse<T> extends RestResponse, ResponseEntityAdaptor<RestSuccessResponse<T>> {

	T getData();

	@Override
	default ResponseEntity<RestSuccessResponse<T>> toResponseEntity() {
		return new ResponseEntity<>(this, getStatus());
	}

}
