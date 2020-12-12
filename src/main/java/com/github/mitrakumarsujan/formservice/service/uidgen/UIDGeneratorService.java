package com.github.mitrakumarsujan.formservice.service.uidgen;

import javax.servlet.http.HttpServletRequest;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.form.OptionField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface UIDGeneratorService {
	String generate(Form form, HttpServletRequest request);

	String generate(FormField formField, HttpServletRequest request);

	String generate(OptionField optionField, HttpServletRequest request);
}
