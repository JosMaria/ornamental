package org.fdryt.ornamental.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RepeatedFamilyNameException.class)
    public ResponseEntity<ErrorExecution> handleFamilyRepeatException(
            RepeatedFamilyNameException exception,
            HttpServletRequest request
    ) {
        ErrorExecution response = ErrorExecution.builder()
                .value(BAD_REQUEST.value())
                .name(BAD_REQUEST.name())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .reason(exception.getMessage())
                .build();

        return ResponseEntity.badRequest().body(response);
    }
}
