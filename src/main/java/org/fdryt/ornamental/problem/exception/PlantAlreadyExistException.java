package org.fdryt.ornamental.problem.exception;

public class PlantAlreadyExistException extends RuntimeException {

    public PlantAlreadyExistException(String reason) {
        super(reason);
    }
}
