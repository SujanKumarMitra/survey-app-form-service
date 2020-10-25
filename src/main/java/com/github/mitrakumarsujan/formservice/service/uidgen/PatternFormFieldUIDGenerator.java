package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formservice.model.form.PatternedFormField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class PatternFormFieldUIDGenerator implements UIDGenerator<PatternedFormField> {

	@Autowired
	private HashFunction hashFunction;

	@Override
	public String generate(PatternedFormField formField) {

		long time;
		synchronized (this) {
			time = System.currentTimeMillis();
		}
		String question = formField.getQuestion();
		boolean required = formField.isRequired();
		String pattern = formField.getPattern();

		return hashFunction.toHash(time, question, required, pattern);
	}

}
