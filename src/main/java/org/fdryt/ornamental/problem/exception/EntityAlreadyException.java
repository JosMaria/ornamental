package org.fdryt.ornamental.problem.exception;

import java.io.Serializable;

public class EntityAlreadyException extends RuntimeException {

    private static final String EXCEPTION_DETAIL_MESSAGE = "%s con valor: %s ya existe.";

    public EntityAlreadyException(Class<?> entityClass, Serializable value) {
        super(String.format(EXCEPTION_DETAIL_MESSAGE, entityClass.getSimpleName(), value));
    }
}
