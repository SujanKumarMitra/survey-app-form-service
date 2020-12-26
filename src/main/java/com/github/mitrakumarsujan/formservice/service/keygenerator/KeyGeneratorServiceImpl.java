package com.github.mitrakumarsujan.formservice.service.keygenerator;

import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formmodel.model.form.Form;

import java.util.UUID;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class KeyGeneratorServiceImpl implements KeyGeneratorService {

	@Override
	public String generate(Form template) {
		return UUID.randomUUID().toString();
	}

}
