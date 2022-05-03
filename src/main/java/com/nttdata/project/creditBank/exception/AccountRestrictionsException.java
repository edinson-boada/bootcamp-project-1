package com.nttdata.project.creditBank.exception;

public class AccountRestrictionsException extends RuntimeException{
    public AccountRestrictionsException(String message) {
        super(message);
    }
}
