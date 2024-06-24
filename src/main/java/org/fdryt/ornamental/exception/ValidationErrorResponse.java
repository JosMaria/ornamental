package org.fdryt.ornamental.exception;

import java.time.LocalDateTime;
import java.util.Map;

class ValidationErrorResponse {
    private final int value;
    private final String name;
    private final String path;
    private final LocalDateTime timestamp;
    private final Map<String, String> fieldErrors;

    public ValidationErrorResponse(int value, String name, String path, LocalDateTime timestamp, Map<String, String> fieldErrors) {
        this.value = value;
        this.name = name;
        this.path = path;
        this.timestamp = timestamp;
        this.fieldErrors = fieldErrors;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}
