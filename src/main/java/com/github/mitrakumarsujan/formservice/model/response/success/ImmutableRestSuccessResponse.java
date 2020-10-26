package com.github.mitrakumarsujan.formservice.model.response.success;

import org.springframework.http.HttpStatus;

import com.github.mitrakumarsujan.formservice.model.response.RestSuccessResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public class ImmutableRestSuccessResponse<T> implements RestSuccessResponse<T> {

	private final RestSuccessResponse<T> delegatee;

	public ImmutableRestSuccessResponse(RestSuccessResponse<T> delegatee) {
		this.delegatee = delegatee;
	}

	public ImmutableRestSuccessResponse(HttpStatus status, String message, T data) {
		this.delegatee = new RestSuccessResponse<>() {

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
		};
	}

	@Override
	public HttpStatus getStatus() {
		return delegatee.getStatus();
	}

	@Override
	public int getCode() {
		return delegatee.getCode();
	}

	@Override
	public String getMessage() {
		return delegatee.getMessage();
	}

	@Override
	public T getData() {
		return delegatee.getData();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RestSuccessResponse [status=");
		builder.append(getStatus());
		builder.append(", code=");
		builder.append(getCode());
		builder.append(", message=");
		builder.append(getMessage());
		builder.append(", data=");
		builder.append(getData());
		builder.append("]");
		return builder.toString();
	}

}
