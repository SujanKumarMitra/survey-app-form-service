package com.github.mitrakumarsujan.formservice.service;

import com.github.mitrakumarsujan.formmodel.exception.FormNotFoundException;
import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormTemplate;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
public interface FormService {

	Form createForm(FormTemplate template);

	Form getForm(String formId) throws FormNotFoundException;
}
