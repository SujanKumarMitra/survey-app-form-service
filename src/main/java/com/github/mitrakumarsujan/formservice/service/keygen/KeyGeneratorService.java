package com.github.mitrakumarsujan.formservice.service.keygen;

import javax.servlet.http.HttpServletRequest;

import com.github.mitrakumarsujan.formservice.model.form.Form;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface KeyGeneratorService {
	String generate(Form form, HttpServletRequest request);
}
