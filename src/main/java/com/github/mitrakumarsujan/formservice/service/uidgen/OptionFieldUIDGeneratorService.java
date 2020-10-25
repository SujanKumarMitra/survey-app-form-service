package com.github.mitrakumarsujan.formservice.service.uidgen;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formservice.model.form.OptionField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class OptionFieldUIDGeneratorService {

	@Autowired
	private OptionFieldUIDGenerator optionFieldUIDGenerator;

	public String generate(OptionField optionField, HttpServletRequest request) {
		return optionFieldUIDGenerator.generate(optionField);
	}

}
