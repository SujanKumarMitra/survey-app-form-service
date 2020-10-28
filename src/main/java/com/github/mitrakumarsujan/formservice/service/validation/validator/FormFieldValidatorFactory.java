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
import com.github.mitrakumarsujan.formmodel.model.form.DateField;
import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.form.RadioButtonField;
import com.github.mitrakumarsujan.formmodel.model.form.TextBoxField;
import com.github.mitrakumarsujan.formmodel.model.form.TimeField;
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
	public <T extends FormField> FormFieldValidator<T, Response> getValidator(Class<? extends T> fieldType) {
		return (FormFieldValidator<T, Response>) map.get(fieldType);

	}

	@SuppressWarnings({ "unchecked" })
	public <T extends FormField, R extends Response> FormFieldValidator<T, R> getValidator(Class<? extends T> fieldType,
			Class<? extends R> responseType) {
		return (FormFieldValidator<T, R>) map.get(fieldType);
	}

	public <T extends FormField> void registerValidator(Class<? extends T> fieldType,
			FormFieldValidator<? extends T, ? extends Response> validator) {
		map.put(fieldType, validator);
		LOGGER.info("{} registered for {}",validator.getClass().getSimpleName(),fieldType.getSimpleName());
	}
	
	@PostConstruct
	private void registerKnownValidators() {
		registerValidator(CheckBoxField.class, getInstance(CheckBoxFieldValidator.class));
		registerValidator(DateField.class, getInstance(DateFieldValidator.class));
		registerValidator(RadioButtonField.class, getInstance(RadioButtonFieldValidator.class));
		registerValidator(TimeField.class, getInstance(TimeFieldValidator.class));
		registerValidator(TextBoxField.class, getInstance(TextBoxFieldValidator.class));
	}
	
	private <T> T getInstance(Class<? extends T> classType) {
		return context.getBean(classType);
	}

}
