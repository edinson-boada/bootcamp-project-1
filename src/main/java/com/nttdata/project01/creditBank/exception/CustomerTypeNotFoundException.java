package com.nttdata.project01.creditBank.exception;

public class CustomerTypeNotFoundException extends RuntimeException {
    public CustomerTypeNotFoundException(String message) {
        super(message);
    }
}
