package org.fdryt.ornamental.problem.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ValidationErrorResponse extends ErrorResponse {

    private Map<String, String> fieldErrors;

    public ValidationErrorResponse(LocalDateTime timestamp, int status, String error,
                                   String path, Map<String, String> fieldErrors) {
        super(timestamp, status, error, path);
        this.fieldErrors = fieldErrors;
    }
}
