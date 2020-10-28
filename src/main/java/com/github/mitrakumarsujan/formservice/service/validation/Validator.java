package com.github.mitrakumarsujan.formservice.service.validation;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
public interface Validator<F, R> {

	boolean validate(F field, R response);
}