package com.github.mitrakumarsujan.formservice.service.defaultresponsegenerator;

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

/**
 * @author skmitra
 * @since 2020-11-09
 */
@Service
public class DefaultResponseGeneratorFactoryImpl implements DefaultResponseGeneratorFactory {

	@Autowired
	private ApplicationContext context;

	private Map<Class<? extends FormField>, DefaultResponseGenerator<? extends FormField>> instanceMap;

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultResponseGeneratorFactoryImpl.class);

	public DefaultResponseGeneratorFactoryImpl() {
		instanceMap = new HashMap<>();
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <F extends FormField> DefaultResponseGenerator<F> getGenerator(Class<? extends F> fieldType) {
		return (DefaultResponseGenerator<F>) instanceMap.get(fieldType);
	}

	@Override
	public <F extends FormField> void registerGenerator(Class<? extends F> fieldType,
			DefaultResponseGenerator<? extends F> generator) {
		instanceMap.put(fieldType, generator);
		LOGGER.info("{} registered for field {}", generator	.getClass().getSimpleName(),fieldType.getSimpleName());
	}

	@PostConstruct
	private void registerKnownGenerators() {
		registerGenerator(DateField.class, context.getBean(DefaultDateResponseGenerator.class));
		registerGenerator(TimeField.class, context.getBean(DefaultTimeResponseGenerator.class));
		registerGenerator(TextBoxField.class, context.getBean(DefaultTextBoxResponseGenerator.class));
		registerGenerator(CheckBoxField.class, context.getBean(DefaultCheckBoxResponseGenerator.class));
		registerGenerator(RadioButtonField.class, context.getBean(DefaultRadioButtonResponseGenerator.class));
	}
}
