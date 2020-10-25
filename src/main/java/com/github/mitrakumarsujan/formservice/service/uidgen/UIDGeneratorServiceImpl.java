package com.github.mitrakumarsujan.formservice.service.uidgen;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formservice.model.form.Form;
import com.github.mitrakumarsujan.formservice.model.form.FormField;
import com.github.mitrakumarsujan.formservice.model.form.OptionField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class UIDGeneratorServiceImpl implements UIDGeneratorService {

	@Autowired
	private FormUIDGeneratorService formUIDGeneratorFacade;

	@Autowired
	private FormFieldUIDGeneratorService formFieldUIDGeneratorFacade;

	@Autowired
	private OptionFieldUIDGeneratorService optionFieldUIDGeneratorFacade;

	@Override
	public String generate(Form form, HttpServletRequest request) {
		return formUIDGeneratorFacade.generate(form, request);
	}

	@Override
	public String generate(FormField formField, HttpServletRequest request) {
		return formFieldUIDGeneratorFacade.generate(formField, request);
	}

	@Override
	public String generate(OptionField optionField, HttpServletRequest request) {
		return optionFieldUIDGeneratorFacade.generate(optionField, request);
	}

}
