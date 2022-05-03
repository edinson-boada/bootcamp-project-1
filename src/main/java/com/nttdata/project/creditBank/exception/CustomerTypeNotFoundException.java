package com.nttdata.project.creditBank.exception;

public class CustomerTypeNotFoundException extends RuntimeException {
    public CustomerTypeNotFoundException(String message) {
        super(message);
    }
}
