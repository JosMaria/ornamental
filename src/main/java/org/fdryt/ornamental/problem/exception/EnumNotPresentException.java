package org.fdryt.ornamental.problem.exception;

import lombok.Getter;

import static java.lang.String.format;

@Getter
public class EnumNotPresentException extends EnumConstantNotPresentException{

    private final String message;

    public EnumNotPresentException(Class<? extends Enum<?>> enumType, String constantName) {
        super(enumType, constantName);
        message = format("Enum %s with value: %s could not be found or it does not exist", enumType.getSimpleName(), constantName);
    }
}
