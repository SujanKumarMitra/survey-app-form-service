package com.github.mitrakumarsujan.formservice.service.idgenerator;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface IdGenerator<T> {

	String generate(T type);
}
