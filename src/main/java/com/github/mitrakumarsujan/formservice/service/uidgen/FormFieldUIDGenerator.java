package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.FormField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class FormFieldUIDGenerator implements UIDGenerator<FormField> {

	@Autowired
	private HashFunction hashFunction;

	@Override
	public String generate(FormField formField) {

		long time;
		synchronized (this) {
			time = System.currentTimeMillis();
		}
		String question = formField.getQuestion();
		boolean required = formField.isRequired();
		return hashFunction.toHash(time, question, required);
	}

}
