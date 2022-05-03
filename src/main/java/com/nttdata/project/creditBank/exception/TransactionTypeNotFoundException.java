package com.nttdata.project.creditBank.exception;

public class TransactionTypeNotFoundException extends RuntimeException {
    public TransactionTypeNotFoundException(String message) {
        super(message);
    }
}
