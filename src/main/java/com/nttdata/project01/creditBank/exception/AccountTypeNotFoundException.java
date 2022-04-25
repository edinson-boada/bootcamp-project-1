package com.nttdata.project01.creditBank.exception;

public class AccountTypeNotFoundException extends RuntimeException {
	public AccountTypeNotFoundException(String message) {
		super(message);
	}
}
