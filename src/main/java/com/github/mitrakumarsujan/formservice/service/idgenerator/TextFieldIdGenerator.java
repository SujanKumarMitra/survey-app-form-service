package com.github.mitrakumarsujan.formservice.service.idgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TextField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class TextFieldIdGenerator implements IdGenerator<TextField> {

	@Autowired
	private FormFieldIdGenerator delegatee;

	@Override
	public String generate(TextField textBox) {
		return delegatee.generate(textBox);
	}

}
