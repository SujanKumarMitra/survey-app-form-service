package com.github.mitrakumarsujan.formservice.model.response.success;

import org.springframework.http.HttpStatus;

import com.github.mitrakumarsujan.formservice.model.response.RestSuccessResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public class SingleDataRestSuccessResponeBuilder<T> implements RestSuccessResponseBuilder<T> {

	private MutableRestSuccessResponse<T> response;

	public SingleDataRestSuccessResponeBuilder(MutableRestSuccessResponse<T> response) {
		this.response = response;
	}

	public SingleDataRestSuccessResponeBuilder() {
		this(new MutableRestSuccessResponse<>());
	}

	@Override
	public RestSuccessResponseBuilder<T> withData(T data) {
		response.setData(data);
		return this;
	}

	@Override
	public RestSuccessResponseBuilder<T> withStatus(HttpStatus status) {
		response.setStatus(status);
		return this;
	}

	@Override
	public RestSuccessResponseBuilder<T> withMessage(String message) {
		response.setMessage(message);
		return this;
	}

	@Override
	public RestSuccessResponse<T> build() {
		return new ImmutableRestSuccessResponse<>(response);
	}

}
