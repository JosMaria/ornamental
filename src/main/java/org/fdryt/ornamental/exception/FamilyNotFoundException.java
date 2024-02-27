package org.fdryt.ornamental.exception;

public class FamilyNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Family with ID: '%s' does not found.";

    public FamilyNotFoundException(String id) {
        super(MESSAGE.formatted(id));
    }
}
