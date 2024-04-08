package org.fdryt.ornamental.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorExecution {

    private int value;
    private String name;
    private String path;
    private LocalDateTime timestamp;
    private String reason;
}
