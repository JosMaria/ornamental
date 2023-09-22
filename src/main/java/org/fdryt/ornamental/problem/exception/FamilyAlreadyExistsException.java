package org.fdryt.ornamental.problem.exception;

import lombok.Getter;

@Getter
public class FamilyAlreadyExistsException extends RuntimeException {

    private final String messageToClient;

    public FamilyAlreadyExistsException(String name) {
        super("Family named: %s already exists.".formatted(name));
        messageToClient = "La familia con el nombre: %s ya existe".formatted(name);
    }
}
