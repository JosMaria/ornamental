package org.fdryt.ornamental.exception;

public class RepeatedFamilyNameException extends RuntimeException {

    private final static String MESSAGE = "family name: '%s' already exists.";

    public RepeatedFamilyNameException(String familyName) {
        super(MESSAGE.formatted(familyName));
    }
}
