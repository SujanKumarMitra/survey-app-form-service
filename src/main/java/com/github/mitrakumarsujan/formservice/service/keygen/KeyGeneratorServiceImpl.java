package com.github.mitrakumarsujan.formservice.service.keygen;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.github.mitrakumarsujan.formservice.model.form.Form;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-25
 */
@Service
public class KeyGeneratorServiceImpl implements KeyGeneratorService {

	@Override
	public String generate(Form template, HttpServletRequest request) {
		String sessionId = request	.getSession()
									.getId();
		request.changeSessionId();
		return sessionId;
	}

}
