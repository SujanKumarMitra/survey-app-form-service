package com.github.mitrakumarsujan.formservice.service.idgenerator;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class FormFieldIdGenerator implements IdGenerator<FormField> {

	@Autowired
	private HashFunction hashFunction;

	@Override
	public String generate(FormField formField) {

		String uuid;
		synchronized (this) {
			uuid = UUID.randomUUID().toString();
		}
		String question = formField.getQuestion();
		boolean required = formField.isRequired();
		return hashFunction.toHash(uuid, question, required);
	}

}
