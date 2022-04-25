package com.nttdata.project01.creditBank.exception;

public class AccountRestrictionsException extends RuntimeException{
    public AccountRestrictionsException(String message) {
        super(message);
    }
}
