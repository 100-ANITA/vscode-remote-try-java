package com.mycompany.bank.exceptions;

public class AccountOpeningException extends RuntimeException {
    public AccountOpeningException(String message) {
        super(message);
    }
}
