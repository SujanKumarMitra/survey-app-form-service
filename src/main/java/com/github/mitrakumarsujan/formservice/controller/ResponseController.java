package com.github.mitrakumarsujan.formservice.controller;

import java.time.ZoneId;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.model.response.RestSuccessResponse;
import com.github.mitrakumarsujan.formmodel.model.response.success.RestSuccessResponseBuilder;
import com.github.mitrakumarsujan.formmodel.model.response.success.RestSuccessResponseBuilderFactory;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/v1/submit")
public class ResponseController {

	@Autowired
	private RestSuccessResponseBuilderFactory responseBuilderFactory;

	@PostMapping("/{formUid}")
	public ResponseEntity<RestSuccessResponse<FormResponse>> fun(ZoneId localeZoneId,
			@PathVariable(name = "formUid", required = true) String formUid,
			@RequestBody @Valid FormResponse response) {

		setZoneIdIfAbsent(response, localeZoneId);

		return getBuilder()	.withData(response)
							.withStatus(HttpStatus.CREATED)
							.build()
							.toResponseEntity();
	}

	private void setZoneIdIfAbsent(FormResponse response, ZoneId localeZoneId) {
		if (response.getZoneId() == null)
			response.setZoneId(localeZoneId);
	}

	private RestSuccessResponseBuilder<FormResponse> getBuilder() {
		return responseBuilderFactory.getSingleDataBuilder(FormResponse.class);
	}
}
