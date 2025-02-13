package com.github.mitrakumarsujan.formservice.service.idgenerator;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.OptionField;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Component
public class OptionFieldIdGenerator implements IdGenerator<OptionField> {

	@Autowired
	private HashFunction hashFunction;

	@Override
	public String generate(OptionField option) {

		String uuid;
		synchronized (this) {
			uuid = UUID.randomUUID().toString();
		}
		String text = option.getText();
		return hashFunction.toHash(uuid, text);
	}

}
