package org.fdryt.ornamental.problem.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
public class ValidationErrorResponse extends ErrorResponse {

    private Map<String, String> fieldErrors;
}
