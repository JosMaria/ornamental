package org.fdryt.ornamental.problem;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.fdryt.ornamental.problem.exception.PlantAlreadyExistException;
import org.fdryt.ornamental.problem.response.ProcessErrorResponse;
import org.fdryt.ornamental.problem.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlantAlreadyExistException.class)
    public ResponseEntity<ProcessErrorResponse> handlePlantAlreadyExistException(PlantAlreadyExistException exception,
                                                                                 HttpServletRequest httpServletRequest) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        String message = exception.getMessage();
        log.warn(message);

        ProcessErrorResponse errorResponse = new ProcessErrorResponse(LocalDateTime.now(),
                httpStatus.value(), httpStatus.name(), httpServletRequest.getRequestURI(), message);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                                         HttpServletRequest httpServletRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> fieldErrors = new HashMap<>();
        if (exception.hasFieldErrors()) {
            exception.getFieldErrors()
                    .forEach(fieldError -> fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(LocalDateTime.now(),
                httpStatus.value(), httpStatus.name(), httpServletRequest.getRequestURI(), fieldErrors);

        return ResponseEntity.badRequest().body(validationErrorResponse);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ProcessErrorResponse> handleInvalidFormatException(InvalidFormatException exception,
                                                                                HttpServletRequest httpServletRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String message = String.format("Value %s not founded", exception.getValue());

        ProcessErrorResponse processErrorResponse = new ProcessErrorResponse(LocalDateTime.now(), httpStatus.value(),
                httpStatus.name(), httpServletRequest.getRequestURI(), message);
        return ResponseEntity.badRequest().body(processErrorResponse);
    }
}
