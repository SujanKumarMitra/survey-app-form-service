package com.github.mitrakumarsujan.formservice.service.uidgen;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class FormFieldUIDGeneratorService {

	@Autowired
	private FormFieldUIDGeneratorFactoryImpl generatorFactory;

	public String generate(FormField formField, HttpServletRequest request) {
		UIDGenerator<FormField> generator = generatorFactory.getGenerator(formField.getClass());
		return generator.generate(formField);
	}

}
