package org.mql.jee.trainingcenter.exceptions;

public class TrainingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TrainingException(String message) {
        super(message);
    }
}