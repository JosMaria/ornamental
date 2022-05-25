package org.fdryt.ornamental.problem.exception;

import lombok.Getter;

@Getter
public class PlantAlreadyExistException extends RuntimeException {

    public PlantAlreadyExistException(String reason) {
        super(reason);
    }
}
