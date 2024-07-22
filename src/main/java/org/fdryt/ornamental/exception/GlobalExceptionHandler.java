package org.fdryt.ornamental.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        if (exception.hasFieldErrors()) {
            exception.getFieldErrors()
                    .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
        }
        Set<String> message = new HashSet<>(fieldErrors.values());
        log.warn("Exception thrown, it does not satisfy the validations. and contains the messages '{}'", message);
        return ResponseEntity.badRequest()
                .body(
                        new ValidationErrorResponse(
                                BAD_REQUEST.value(),
                                BAD_REQUEST.name(),
                                request.getRequestURI(),
                                LocalDateTime.now(),
                                fieldErrors)
                );
    }

    @ExceptionHandler(NewsNotAvailableException.class)
    public ResponseEntity<ErrorExecution> handleNewsNotAvailableException(
            NewsNotAvailableException exception,
            HttpServletRequest request) {
        ErrorExecution response = buildErrorExecution(BAD_REQUEST, request.getRequestURI(), exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<ErrorExecution> handleNewsNotFoundException(
            NewsNotFoundException exception,
            HttpServletRequest request) {
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
