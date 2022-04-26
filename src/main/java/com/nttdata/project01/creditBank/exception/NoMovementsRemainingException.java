package com.nttdata.project01.creditBank.exception;

public class NoMovementsRemainingException extends RuntimeException {
    public NoMovementsRemainingException(String message) {
        super(message);
    }
}
