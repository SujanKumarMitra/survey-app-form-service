package com.github.mitrakumarsujan.formservice.service.idgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class FormFieldIdGeneratorService {

	@Autowired
	private FormFieldIdGeneratorFactoryImpl generatorFactory;

	public String generate(FormField formField) {
		IdGenerator<FormField> generator = generatorFactory.getGenerator(formField.getClass());
		return generator.generate(formField);
	}

}
