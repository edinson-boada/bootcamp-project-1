package com.nttdata.project01.creditBank.controller;

import com.nttdata.project01.creditBank.exception.EmpresarialAccountException;
import com.nttdata.project01.creditBank.exception.ErrorMessage;
import com.nttdata.project01.creditBank.exception.PersonalAccountException;
import com.nttdata.project01.creditBank.exception.TypeAccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(TypeAccountNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleTypeAccountNotFound(TypeAccountNotFoundException ex) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(PersonalAccountException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlePersonalAccount(PersonalAccountException ex) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        return message;
    }

    @ExceptionHandler(EmpresarialAccountException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleEmpresarialAccount(EmpresarialAccountException ex) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        return message;
    }
}
