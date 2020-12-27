package com.github.mitrakumarsujan.formservice.service.idgenerator;

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
public class FormFieldIdGeneratorFactoryImpl implements FormFieldIdGeneratorFactory {

	@Autowired
	private ApplicationContext context;

	private Map<Class<? extends FormField>, IdGenerator<? extends FormField>> instanceMap;

	private static final Logger LOGGER = LoggerFactory.getLogger(FormFieldIdGeneratorFactoryImpl.class);

	public FormFieldIdGeneratorFactoryImpl() {
		this.instanceMap = new HashMap<>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <X extends FormField> IdGenerator<X> getGenerator(Class<? extends X> classType) {
		return (IdGenerator<X>) instanceMap.get(classType);
	}

	@Override
	public <X extends FormField> void registerGenerator(Class<? extends X> fieldType,
			IdGenerator<? extends X> idGenerator) {
		instanceMap.put(fieldType, idGenerator);
		LOGGER.info("{} registered for {}", idGenerator.getClass().getSimpleName(), fieldType.getSimpleName());
	}


	@PostConstruct
	private void registerKnownGenerators() {
		registerGenerator(CheckBoxField.class, context.getBean(CheckBoxIdGenerator.class));
		registerGenerator(DateField.class, context.getBean(DateFieldIdGenerator.class));
		registerGenerator(TimeField.class, context.getBean(TimeFieldIdGenerator.class));
		registerGenerator(TextField.class, context.getBean(TextFieldIdGenerator.class));
		registerGenerator(RadioField.class, context.getBean(RadioIdGenerator.class));
	}

}
