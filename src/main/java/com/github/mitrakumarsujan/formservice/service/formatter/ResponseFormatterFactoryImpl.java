package com.github.mitrakumarsujan.formservice.service.formatter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.CheckBoxResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.DateResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.RadioResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.Response;
import com.github.mitrakumarsujan.formmodel.model.formresponse.TextFieldResponse;
import com.github.mitrakumarsujan.formmodel.model.formresponse.TimeResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Service
public class ResponseFormatterFactoryImpl implements ResponseFormatterFactory {

	Map<Class<? extends Response>, FormResponseFormatter<? extends FormField, ? extends Response>> map;

	@Autowired
	private ApplicationContext context;

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseFormatterFactoryImpl.class);

	public ResponseFormatterFactoryImpl() {
		map = new HashMap<>();
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <R extends Response> FormResponseFormatter<FormField, R> getFormatter(Class<? extends R> responseType) {
		return (FormResponseFormatter<FormField, R>) map.get(responseType);
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public <F extends FormField, R extends Response> FormResponseFormatter<F, R> getFormatter(Class<? extends R> responseType,
			Class<? extends F> fieldType) {
		return (FormResponseFormatter<F, R>) map.get(responseType);
	}

	@Override
	public <R extends Response> void registerFormatter(Class<? extends R> responseType,
			FormResponseFormatter<? extends FormField, R> formatter) {
		map.put(responseType, formatter);
		
		LOGGER.info("{} registered for {}", formatter.getClass().getSimpleName(),responseType.getSimpleName());
	}
	
	@PostConstruct
	private void registerKnownFormatters() {
		registerFormatter(DateResponse.class, context.getBean(DateResponseFormatter.class));
		registerFormatter(TimeResponse.class, context.getBean(TimeResponseFormatter.class));
		registerFormatter(TextFieldResponse.class, context.getBean(TextFieldResponseFormatter.class));
		registerFormatter(CheckBoxResponse.class, context.getBean(CheckBoxResponseFormatter.class));
		registerFormatter(RadioResponse.class, context.getBean(RadioResponseFormatter.class));
	}
}
