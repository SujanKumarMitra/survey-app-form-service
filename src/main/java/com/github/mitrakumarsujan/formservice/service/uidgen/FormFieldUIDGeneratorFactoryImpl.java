package com.github.mitrakumarsujan.formservice.service.uidgen;

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
import com.github.mitrakumarsujan.formmodel.model.form.RadioField;
import com.github.mitrakumarsujan.formmodel.model.form.TextField;
import com.github.mitrakumarsujan.formmodel.model.form.TimeField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class FormFieldUIDGeneratorFactoryImpl implements FormFieldUIDGeneratorFactory {

	@Autowired
	private ApplicationContext context;

	private Map<Class<? extends FormField>, UIDGenerator<? extends FormField>> instanceMap;

	private static final Logger LOGGER = LoggerFactory.getLogger(FormFieldUIDGeneratorFactoryImpl.class);

	public FormFieldUIDGeneratorFactoryImpl() {
		this.instanceMap = new HashMap<>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <X extends FormField> UIDGenerator<X> getGenerator(Class<? extends X> classType) {
		return (UIDGenerator<X>) instanceMap.get(classType);
	}

	@Override
	public <X extends FormField> void registerGenerator(Class<? extends X> fieldType,
			UIDGenerator<? extends X> uidGenerator) {
		instanceMap.put(fieldType, uidGenerator);
		LOGGER.info("{} registered for {}", uidGenerator.getClass().getSimpleName(), fieldType.getSimpleName());
	}


	@PostConstruct
	private void registerKnownGenerators() {
		registerGenerator(CheckBoxField.class, context.getBean(CheckBoxUIDGenerator.class));
		registerGenerator(DateField.class, context.getBean(DateFieldUIDGenerator.class));
		registerGenerator(TimeField.class, context.getBean(TimeFieldUIDGenerator.class));
		registerGenerator(TextField.class, context.getBean(TextFieldUIDGenerator.class));
		registerGenerator(RadioField.class, context.getBean(RadioUIDGenerator.class));
	}

}
