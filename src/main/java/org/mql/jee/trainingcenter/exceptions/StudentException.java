package org.mql.jee.trainingcenter.exceptions;

public class StudentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StudentException(String message) {
        super(message);
    }
}