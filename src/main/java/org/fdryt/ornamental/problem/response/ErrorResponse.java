package org.fdryt.ornamental.problem.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int value;
    private String name;
    private String path;
}
