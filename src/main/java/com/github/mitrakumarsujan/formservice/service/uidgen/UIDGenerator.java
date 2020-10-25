package com.github.mitrakumarsujan.formservice.service.uidgen;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface UIDGenerator<T> {

	String generate(T type);
}
