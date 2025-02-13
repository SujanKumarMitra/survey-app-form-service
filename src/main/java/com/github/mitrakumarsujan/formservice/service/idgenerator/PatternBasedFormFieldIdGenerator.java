package com.github.mitrakumarsujan.formservice.service.idgenerator;

import com.github.mitrakumarsujan.formmodel.model.form.PatternBasedFormField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class PatternBasedFormFieldIdGenerator implements IdGenerator<PatternBasedFormField> {

	@Autowired
	private HashFunction hashFunction;

	@Override
	public String generate(PatternBasedFormField formField) {

		String uuid;
		synchronized (this) {
			uuid = UUID.randomUUID().toString();
		}
		String question = formField.getQuestion();
		boolean required = formField.isRequired();
		String pattern = formField.getPattern();

		return hashFunction.toHash(uuid, question, required, pattern);
	}

}
