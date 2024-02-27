package org.fdryt.ornamental.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RepeatedFamilyNameException.class)
    public ResponseEntity<ErrorExecution> handleRepeatFamilyNameException(
            RepeatedFamilyNameException exception,
            HttpServletRequest request
    ) {
        ErrorExecution response = buildErrorExecution(BAD_REQUEST, request.getRequestURI(), exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(FamilyNotFoundException.class)
    public ResponseEntity<ErrorExecution> handleFamilyNotFoundException(
            FamilyNotFoundException exception,
            HttpServletRequest request
    ) {
        ErrorExecution response = buildErrorExecution(BAD_REQUEST, request.getRequestURI(), exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    private ErrorExecution buildErrorExecution(HttpStatus status, String uri, String message) {
        return ErrorExecution.builder()
                .value(status.value())
                .name(status.name())
                .path(uri)
                .timestamp(LocalDateTime.now())
                .reason(message)
                .build();
    }
}
