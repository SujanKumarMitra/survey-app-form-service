package com.github.mitrakumarsujan.formservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.github.mitrakumarsujan.formservice.service.keygenerator.KeyGeneratorService;
import com.github.mitrakumarsujan.formservice.service.idgenerator.IdGeneratorService;

import static java.text.MessageFormat.format;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class FormServiceImpl implements FormService {

	@Autowired
	private IdGeneratorService idGeneratorService;
	@Autowired
	private KeyGeneratorService keyGeneratorService;
	@Autowired
	@Qualifier("cacheableFormDao")
	private FormDao formDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(FormServiceImpl.class);

	@Override
	public Form createForm(FormTemplate template) {

		String name = template.getName();

		LOGGER.info("setting uids in formTemplate '{}'", name);
		setUIDs(template);
		LOGGER.info("uids set in formTemplate '{}'", name);

		MutableForm form = new MutableForm();
		form.setTemplate(template);

		LOGGER.info("setting uid and key for form '{}'", name);
		String formKey = keyGeneratorService.generate(form);
		String formId = idGeneratorService.generate(form);

		form.setId(formId);
		form.setKey(formKey);
		LOGGER.info("form uid and key set in form '{}'", name);

		Form createdForm = new ImmutableForm(form);
		createdForm = formDao.save(createdForm);

		LOGGER.info("form '{}' created", name);
		return createdForm;
	}

	@Override
	public Form getForm(String formId) throws FormNotFoundException {
		Optional<Form> optionalForm = formDao.find(formId);
		return optionalForm.orElseThrow(() ->
				new FormNotFoundException(format("no form found for id [{0}]",formId)));
	}

	private void setUIDs(FormTemplate template) {
		Stream<OptionField> optionFieldStream = getOptionFieldStream(template);
		Stream<FormField> formFieldStream = getFormFieldStream(template);

		optionFieldStream.forEach(optionField -> setUIDInField(optionField));
		formFieldStream.forEach(formField -> setUIDInField(formField));
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

	private void setUIDInField(OptionField optionField) {
		String text = optionField.getText();
		LOGGER.info("generating uid for OptionField '{}'", text);
		String uid = idGeneratorService.generate(optionField);
		LOGGER.info("uid generated for OptionField '{}'", text);
		optionField.setId(uid);
	}

	private void setUIDInField(FormField formField) {
		String question = formField.getQuestion();
		LOGGER.info("generating uid for FormField '{}'", question);
		String uid = idGeneratorService.generate(formField);
		LOGGER.info("uid generated for FormField '{}'", question);
		formField.setId(uid);
	}

}
