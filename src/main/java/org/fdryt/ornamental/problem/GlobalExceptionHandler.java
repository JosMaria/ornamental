package org.fdryt.ornamental.problem;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.problem.exception.DomainNotFoundException;
import org.fdryt.ornamental.problem.exception.EntityAlreadyException;
import org.fdryt.ornamental.problem.exception.EnumNotPresentException;
import org.fdryt.ornamental.problem.response.ErrorResponse;
import org.fdryt.ornamental.problem.response.ProcessErrorResponse;
import org.fdryt.ornamental.problem.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final HttpStatus httpStatusBadRequest = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDomainNotFoundException(DomainNotFoundException exception, HttpServletRequest request) {
        log.warn(exception.getMessage());
        return responseBadRequest(request.getRequestURI(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        if (exception.hasFieldErrors()) {
            exception.getFieldErrors()
                    .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
        }
        log.warn(String.format("validation error %s", fieldErrors));

        return ResponseEntity
                .badRequest()
                .body(ValidationErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .value(httpStatusBadRequest.value())
                        .name(httpStatusBadRequest.name())
                        .path(request.getRequestURI())
                        .fieldErrors(fieldErrors)
                        .build()
                );
    }

    @ExceptionHandler(EnumNotPresentException.class)
    public ResponseEntity<ErrorResponse> handleEnumNotPresentException(EnumNotPresentException exception, HttpServletRequest request) {
        log.warn(exception.getMessage());
        return responseBadRequest(request.getRequestURI(), exception.getMessage());
    }

    @ExceptionHandler(EntityAlreadyException.class)
    public ResponseEntity<ErrorResponse> handleEntityAlreadyException(EntityAlreadyException exception, HttpServletRequest request) {
        log.warn(exception.getMessage());
        return responseBadRequest(request.getRequestURI(), exception.getMessage());
    }

    private ResponseEntity<ErrorResponse> responseBadRequest(String pathUri, String exceptionMessage) {
        return ResponseEntity.badRequest()
                .body(ProcessErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .value(httpStatusBadRequest.value())
                        .name(httpStatusBadRequest.name())
                        .path(pathUri)
                        .reason(exceptionMessage)
                        .build());
    }
}
