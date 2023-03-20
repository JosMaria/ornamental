package org.fdryt.ornamental.problem.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ProcessErrorResponse extends ErrorResponse {

    private String reason;
}
