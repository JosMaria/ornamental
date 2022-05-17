package org.fdryt.ornamental.problem.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProcessErrorResponse extends ErrorResponse{

    private String message;

    public ProcessErrorResponse(LocalDateTime timestamp, int status, String error, String path, String message) {
        super(timestamp, status, error, path);
        this.message = message;
    }
}
