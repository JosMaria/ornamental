package org.fdryt.ornamental.problem;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.problem.exception.DomainNotFoundException;
import org.fdryt.ornamental.problem.response.ErrorResponse;
import org.fdryt.ornamental.problem.response.ProcessErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDomainNotFoundException(DomainNotFoundException exception, HttpServletRequest request) {
        log.error(exception.getMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return ResponseEntity
                .badRequest()
                .body(ProcessErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .value(httpStatus.value())
                        .name(httpStatus.name())
                        .reason(exception.getMessage())
                        .path(request.getRequestURI())
                        .build()
                );
    }


}
