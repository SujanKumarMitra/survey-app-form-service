package com.github.mitrakumarsujan.formservice.service;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.exception.FormNotFoundException;
import com.github.mitrakumarsujan.formmodel.model.form.ChoiceBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.form.Form;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.form.FormTemplate;
import com.github.mitrakumarsujan.formmodel.model.form.ImmutableForm;
import com.github.mitrakumarsujan.formmodel.model.form.MutableForm;
import com.github.mitrakumarsujan.formmodel.model.form.OptionField;
import com.github.mitrakumarsujan.formservice.dao.FormDao;
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
	@Autowired
	private FormDao formDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(FormServiceImpl.class);

	@Override
	public Form createForm(FormTemplate template, HttpServletRequest request) {

		String name = template.getName();

		LOGGER.info("setting uids in formTemplate '{}'", name);
		setUIDs(template, request);
		LOGGER.info("uids set in formTemplate '{}'", name);

		MutableForm form = new MutableForm();
		form.setTemplate(template);

		LOGGER.info("setting uid and key for form '{}'", name);
		String formKey = keyGeneratorService.generate(form, request);
		String formUID = uidGeneratorService.generate(form, request);

		form.setId(formUID);
		form.setKey(formKey);
		LOGGER.info("form uid and key set in form '{}'", name);

		Form createdForm = new ImmutableForm(form);
		createdForm = formDao.save(createdForm);

		LOGGER.info("form '{}' created", name);
		return createdForm;
	}

	@Override
	public Form getForm(String formId) throws FormNotFoundException {
		return formDao.find(formId);
	}

	private void setUIDs(FormTemplate template, HttpServletRequest request) {
		Stream<OptionField> optionFieldStream = getOptionFieldStream(template);
		Stream<FormField> formFieldStream = getFormFieldStream(template);

		optionFieldStream.forEach(optionField -> setUIDInField(optionField, request));
		formFieldStream.forEach(formField -> setUIDInField(formField, request));
	}

	private Stream<OptionField> getOptionFieldStream(FormTemplate template) {
		return template	.getFields()
						.parallelStream()
						.filter(field -> field instanceof ChoiceBasedFormField)
						.map(field -> (ChoiceBasedFormField) field)
						.map(ChoiceBasedFormField::getOptions)
						.flatMap(List::parallelStream);
	}

	private Stream<FormField> getFormFieldStream(FormTemplate template) {
		return template	.getFields()
						.parallelStream();
	}

	private void setUIDInField(OptionField optionField, HttpServletRequest request) {
		String text = optionField.getText();
		LOGGER.info("generating uid for OptionField '{}'", text);
		String uid = uidGeneratorService.generate(optionField, request);
		LOGGER.info("uid generated for OptionField '{}'", text);
		optionField.setId(uid);
	}

	private void setUIDInField(FormField formField, HttpServletRequest request) {
		String question = formField.getQuestion();
		LOGGER.info("generating uid for FormField '{}'", question);
		String uid = uidGeneratorService.generate(formField, request);
		LOGGER.info("uid generated for FormField '{}'", question);
		formField.setId(uid);
	}

}
