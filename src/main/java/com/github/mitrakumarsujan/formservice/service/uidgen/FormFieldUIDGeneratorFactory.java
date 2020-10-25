package com.github.mitrakumarsujan.formservice.service.uidgen;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formservice.model.form.CheckBoxField;
import com.github.mitrakumarsujan.formservice.model.form.DateField;
import com.github.mitrakumarsujan.formservice.model.form.FormField;
import com.github.mitrakumarsujan.formservice.model.form.RadioButtonField;
import com.github.mitrakumarsujan.formservice.model.form.TextBoxField;
import com.github.mitrakumarsujan.formservice.model.form.TimeField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class FormFieldUIDGeneratorFactory {

	@Autowired
	private ApplicationContext context;

	Map<Class<? extends FormField>, UIDGenerator<? extends FormField>> instanceMap;

	@SuppressWarnings("unchecked")
	public <X extends FormField> UIDGenerator<X> getGenerator(Class<? extends X> classType) {
		return (UIDGenerator<X>) instanceMap.get(classType);
	}

	private void initMap() {
		instanceMap = new HashMap<>();
		instanceMap.put(CheckBoxField.class, getInstance(CheckBoxUIDGenerator.class));
		instanceMap.put(DateField.class, getInstance(DateFieldUIDGenerator.class));
		instanceMap.put(TimeField.class, getInstance(TimeFieldUIDGenerator.class));
		instanceMap.put(TextBoxField.class, getInstance(TextBoxUIDGenerator.class));
		instanceMap.put(RadioButtonField.class, getInstance(RadioButtonUIDGenerator.class));
	}

	<X> X getInstance(Class<? extends X> classType) {
		return context.getBean(classType);
	}
	
	@PostConstruct
	private void setUp() {
		initMap();
	}

}
