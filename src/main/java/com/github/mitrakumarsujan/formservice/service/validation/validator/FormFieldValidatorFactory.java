package com.github.mitrakumarsujan.formservice.service.validation.validator;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.CheckBoxField;
import com.github.mitrakumarsujan.formmodel.model.form.ChoiceBasedFormField;
import com.github.mitrakumarsujan.formmodel.model.form.DateField;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.form.RadioButtonField;
import com.github.mitrakumarsujan.formmodel.model.form.TextBoxField;
import com.github.mitrakumarsujan.formmodel.model.form.TimeField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.ChoiceBasedResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-28
 */
@Service
public class FormFieldValidatorFactory {

	@Autowired
	private ApplicationContext context;

	private static final Logger LOGGER = LoggerFactory.getLogger(FormFieldValidatorFactory.class);

	Map<Class<? extends FormField>, FormFieldValidator<? extends FormField, ? extends Response>> map;

	public FormFieldValidatorFactory() {
		map = new HashMap<>();
	}

	@SuppressWarnings({ "unchecked" })
	public <R extends FormField> FormFieldValidator<R, Response> getValidator(Class<? extends R> fieldType) {
		return (FormFieldValidator<R, Response>) map.get(fieldType);

	}

	@SuppressWarnings({ "unchecked" })
	public <F extends FormField, R extends Response> FormFieldValidator<F, R> getValidator(Class<? extends F> fieldType,
			Class<? extends R> responseType) {
		return (FormFieldValidator<F, R>) map.get(fieldType);
	}

	public <F extends FormField> void registerValidator(Class<? extends F> fieldType,
			FormFieldValidator<? extends F, ? extends Response> validator) {
		map.put(fieldType, validator);
		// @formatter:off
		LOGGER.info("{} registered for {}", validator.getClass().getSimpleName(),fieldType.getSimpleName());
		// @formatter:on
	}

	@PostConstruct
	private void registerKnownValidators() {
		registerValidator(CheckBoxField.class, context.getBean(CheckBoxFieldValidator.class));
		registerValidator(DateField.class, context.getBean(DateFieldValidator.class));
		registerValidator(RadioButtonField.class, context.getBean(RadioButtonFieldValidator.class));
		registerValidator(TimeField.class, context.getBean(TimeFieldValidator.class));
		registerValidator(TextBoxField.class, context.getBean(TextBoxFieldValidator.class));
	}

	@SuppressWarnings({ "unchecked" })
	public <F extends ChoiceBasedFormField, R extends ChoiceBasedResponse> ChoiceBasedFormFieldValidator<F, R> getChoiceBasedValidator(
			Class<? extends F> fieldType, Class<? extends R> responseType) {
		return (ChoiceBasedFormFieldValidator<F, R>) map.get(fieldType);

	}

}
