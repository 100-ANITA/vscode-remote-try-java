package com.example.banking.exception;

import java.math.BigDecimal;

public class MinimumInitialDepositException extends RuntimeException {
    public MinimumInitialDepositException(BigDecimal requiredMinimum) {
        super("Initial deposit must be at least " + requiredMinimum);
    }
}
