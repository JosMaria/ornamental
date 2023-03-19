package org.fdryt.ornamental.problem.exception;

import jakarta.persistence.EntityNotFoundException;

import java.io.Serializable;

public class DomainNotFoundException extends EntityNotFoundException {

    private static final String EXCEPTION_DETAIL_MESSAGE = "%s with ID %s could not be found or it does not exist.";

    public DomainNotFoundException(Class<?> domainClass, Serializable domainId) {
        super(String.format(EXCEPTION_DETAIL_MESSAGE, domainClass.getSimpleName(), domainId));
    }
}
