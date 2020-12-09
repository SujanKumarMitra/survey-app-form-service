package com.github.mitrakumarsujan.formservice.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import com.github.mitrakumarsujan.formmodel.model.restresponse.SuccessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.mitrakumarsujan.formmodel.model.formresponse.FormResponse;
import com.github.mitrakumarsujan.formmodel.model.restresponse.RestSuccessResponse;
import com.github.mitrakumarsujan.formmodel.model.restresponse.success.RestSuccessResponseBuilderFactory;
import com.github.mitrakumarsujan.formservice.service.FormResponseService;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/v1/response")
@CrossOrigin
public class FormResponseController {

    @Autowired
    private RestSuccessResponseBuilderFactory responseBuilderFactory;

    @Autowired
    private FormResponseService formResponseService;

    @PostMapping
    public ResponseEntity<RestSuccessResponse<SuccessMessage>> submitResponse(
            ZoneId localeZoneId,
            @RequestParam(name = "zoneId", required = false) ZoneId clientZoneId,
            @RequestBody @Valid FormResponse response) {

        setTimestamp(localeZoneId, clientZoneId, response);

        formResponseService.submit(response);

        return responseBuilderFactory.getSingleDataBuilder(SuccessMessage.class)
                                     .withData(new SuccessMessage(getSuccessMessage()))
                                     .withStatus(HttpStatus.CREATED)
                                     .build()
                                     .toResponseEntity();
    }

    private String getSuccessMessage() {
        return "Response submitted successfully.";
    }

    private void setTimestamp(ZoneId localeZoneId, ZoneId clientZoneId, FormResponse response) {
        LocalDateTime timestamp;
        if (clientZoneId == null) {
            timestamp = LocalDateTime.now(localeZoneId);
        } else {
            timestamp = LocalDateTime.now(clientZoneId);
        }
        response.setTimestamp(timestamp);
    }

}
