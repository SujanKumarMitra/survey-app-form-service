package com.github.mitrakumarsujan.formservice.service.uidgen;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.form.OptionField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class UIDGeneratorServiceImpl implements UIDGeneratorService {

	@Autowired
	private FormUIDGeneratorService formUIDGeneratorDelegatee;

	@Autowired
	private FormFieldUIDGeneratorService formFieldUIDGeneratorDelegatee;

	@Autowired
	private OptionFieldUIDGeneratorService optionFieldUIDGeneratorDelegatee;

	@Override
	public String generate(Form form, HttpServletRequest request) {
		return formUIDGeneratorDelegatee.generate(form, request);
	}

	@Override
	public String generate(FormField formField, HttpServletRequest request) {
		return formFieldUIDGeneratorDelegatee.generate(formField, request);
	}

	@Override
	public String generate(OptionField optionField, HttpServletRequest request) {
		return optionFieldUIDGeneratorDelegatee.generate(optionField, request);
	}

}
