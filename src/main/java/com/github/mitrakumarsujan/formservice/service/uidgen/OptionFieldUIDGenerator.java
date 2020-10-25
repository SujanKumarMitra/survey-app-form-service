package com.github.mitrakumarsujan.formservice.service.uidgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formservice.model.form.OptionField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class OptionFieldUIDGenerator implements UIDGenerator<OptionField> {

	@Autowired
	private HashFunction hashFunction;

	@Override
	public String generate(OptionField option) {

		long time;
		synchronized (this) {
			time = System.currentTimeMillis();
		}
		String text = option.getText();
		return hashFunction.toHash(time, text);
	}

}
