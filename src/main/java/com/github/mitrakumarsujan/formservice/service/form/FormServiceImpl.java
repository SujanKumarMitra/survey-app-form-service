package com.github.mitrakumarsujan.formservice.service.form;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public Form createForm(FormTemplate template, HttpServletRequest request) {

		setUIDs(template, request);

		MutableForm form = new MutableForm();
		form.setTemplate(template);

		String formKey = keyGeneratorService.generate(form, request);
		String formUID = uidGeneratorService.generate(form, request);

		form.setUid(formUID);
		form.setKey(formKey);

		Form createdForm = new ImmutableForm(form);
		createdForm = formDao.save(form);
		
		return createdForm;
	}

	@Override
	public Form getForm(String formUID) {
		return formDao.find(formUID);
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
		String uid = uidGeneratorService.generate(optionField, request);
		optionField.setUID(uid);
	}

	private void setUIDInField(FormField formField, HttpServletRequest request) {
		String uid = uidGeneratorService.generate(formField, request);
		formField.setUID(uid);
	}

}
