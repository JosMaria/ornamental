package org.fdryt.ornamental.exception;

public class NewsNotFoundException extends RuntimeException {

    private static final String message = "News with ID: %s does not founded.";

    public NewsNotFoundException(String id) {
        super(message.formatted(id));
    }
}
