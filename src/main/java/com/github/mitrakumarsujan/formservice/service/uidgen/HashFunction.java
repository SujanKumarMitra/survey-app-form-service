package com.github.mitrakumarsujan.formservice.service.uidgen;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface HashFunction {

	<T> String toHash(T o);

	@SuppressWarnings("unchecked")
	<T> String toHash(T... o);

}