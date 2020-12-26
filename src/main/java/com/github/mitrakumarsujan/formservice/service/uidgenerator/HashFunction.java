package com.github.mitrakumarsujan.formservice.service.uidgenerator;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface HashFunction {

	String toHash(Object o);

	String toHash(Object... o);

}