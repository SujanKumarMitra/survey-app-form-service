package com.github.mitrakumarsujan.formservice.model.response.success;

import org.springframework.http.HttpStatus;

import com.github.mitrakumarsujan.formservice.model.response.RestSuccessResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public class MutableRestSuccessResponse<T> implements RestSuccessResponse<T> {

	private HttpStatus status;
	private String message;
	private T data;

	public MutableRestSuccessResponse() {
		this(HttpStatus.OK);
	}

	public MutableRestSuccessResponse(HttpStatus status) {
		this(status, null);
	}

	public MutableRestSuccessResponse(T data) {
		this(HttpStatus.OK, data);
	}

	public MutableRestSuccessResponse(HttpStatus status, T data) {
		this(status, status.getReasonPhrase(), data);
	}

	public MutableRestSuccessResponse(HttpStatus status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	@Override
	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public int getCode() {
		return status.value();
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public T getData() {
		return data;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(T data) {
		this.data = data;
	}

}
