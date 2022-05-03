package com.nttdata.project.creditBank.exception;

public class NoMovementsRemainingException extends RuntimeException {
    public NoMovementsRemainingException(String message) {
        super(message);
    }
}
