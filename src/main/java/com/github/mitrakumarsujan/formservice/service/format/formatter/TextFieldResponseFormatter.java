package com.github.mitrakumarsujan.formservice.service.format.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.mitrakumarsujan.formmodel.model.form.TextField;
import com.github.mitrakumarsujan.formmodel.model.formresponse.TextFieldResponse;

/**
 * @author Sujan Kumar Mitra
 * @since 2020-11-04
 */
@Component
public class TextFieldResponseFormatter implements FormResponseFormatter<TextField, TextFieldResponse> {

    private static final String ENTER_KEY = String.valueOf((char) 10);
    private static final String NEW_LINE = System.lineSeparator();

    @Override
    public void format(TextField field, TextFieldResponse response) {
        String answer = response.getAnswer();
        answer = answer
                .replaceAll(NEW_LINE, "")
                .replaceAll(ENTER_KEY, "");
        response.setAnswer(answer);
    }

}
