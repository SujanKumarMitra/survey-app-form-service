package com.github.mitrakumarsujan.formservice.model.response.success;

import org.springframework.http.HttpStatus;

import com.github.mitrakumarsujan.formservice.model.response.RestSuccessResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface RestSuccessResponseBuilder<T> {

	RestSuccessResponseBuilder<T> withData(T data);

	RestSuccessResponseBuilder<T> withStatus(HttpStatus status);

	RestSuccessResponseBuilder<T> withMessage(String message);

	RestSuccessResponse<T> build();

}
