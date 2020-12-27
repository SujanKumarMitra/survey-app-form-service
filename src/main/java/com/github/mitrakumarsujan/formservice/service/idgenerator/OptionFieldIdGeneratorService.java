package com.github.mitrakumarsujan.formservice.service.idgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.OptionField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class OptionFieldIdGeneratorService {

	@Autowired
	private OptionFieldIdGenerator optionFieldIdGenerator;

	public String generate(OptionField optionField) {
		return optionFieldIdGenerator.generate(optionField);
	}

}
