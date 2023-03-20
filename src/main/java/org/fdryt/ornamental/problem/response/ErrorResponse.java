package org.fdryt.ornamental.problem.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public abstract class ErrorResponse {

    protected LocalDateTime timestamp;
    protected int value;
    protected String name;
    protected String path;
}
