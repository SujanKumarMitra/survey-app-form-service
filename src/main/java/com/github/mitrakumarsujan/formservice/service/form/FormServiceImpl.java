package com.github.mitrakumarsujan.formservice.service.form;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formservice.model.form.ChoiceTypeFormField;
import com.github.mitrakumarsujan.formservice.model.form.Form;
import com.github.mitrakumarsujan.formservice.model.form.FormField;
import com.github.mitrakumarsujan.formservice.model.form.FormTemplate;
import com.github.mitrakumarsujan.formservice.model.form.ImmutableForm;
import com.github.mitrakumarsujan.formservice.model.form.MutableForm;
import com.github.mitrakumarsujan.formservice.model.form.OptionField;
import com.github.mitrakumarsujan.formservice.service.keygen.KeyGeneratorService;
import com.github.mitrakumarsujan.formservice.service.uidgen.UIDGeneratorService;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class FormServiceImpl implements FormService {

	@Autowired
	private UIDGeneratorService uidGeneratorService;
	@Autowired
	private KeyGeneratorService keyGeneratorService;

	@Override
	public Form createForm(FormTemplate template, HttpServletRequest request) {

		setUIDs(template, request);

		MutableForm form = new MutableForm();
		form.setTemplate(template);

		String formKey = keyGeneratorService.generate(form, request);
		String formUID = uidGeneratorService.generate(form, request);

		form.setUid(formUID);
		form.setKey(formKey);

		return new ImmutableForm(form);
	}

	private void setUIDs(FormTemplate template, HttpServletRequest request) {
		Stream<OptionField> optionFieldStream = getOptionFieldStream(template);
		Stream<FormField> formFieldStream = getFormFieldStream(template);

		optionFieldStream.forEach(optionField -> setUIDInOptionField(optionField, request));
		formFieldStream.forEach(formField -> setUIDInFormField(formField, request));
	}

	private Stream<OptionField> getOptionFieldStream(FormTemplate template) {
		return template	.getFields()
						.parallelStream()
						.filter(field -> field instanceof ChoiceTypeFormField)
						.map(field -> (ChoiceTypeFormField) field)
						.map(ChoiceTypeFormField::getOptions)
						.flatMap(List::parallelStream);
	}

	private Stream<FormField> getFormFieldStream(FormTemplate template) {
		return template	.getFields()
						.parallelStream();
	}

	private void setUIDInOptionField(OptionField optionField, HttpServletRequest request) {
		String uid = uidGeneratorService.generate(optionField, request);
		optionField.setUID(uid);
	}

	private void setUIDInFormField(FormField formField, HttpServletRequest request) {
		String uid = uidGeneratorService.generate(formField, request);
		formField.setUID(uid);
	}

}
