package com.github.mitrakumarsujan.formservice.service.uidgenerator;

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
	public String generate(Form form) {
		return formUIDGeneratorDelegatee.generate(form);
	}

	@Override
	public String generate(FormField formField) {
		return formFieldUIDGeneratorDelegatee.generate(formField);
	}

	@Override
	public String generate(OptionField optionField) {
		return optionFieldUIDGeneratorDelegatee.generate(optionField);
	}

}
