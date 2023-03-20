package org.fdryt.ornamental.problem.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@SuperBuilder
public class ValidationErrorResponse extends ErrorResponse {

    private Map<String, String> fieldErrors;
}
