package com.elitebank.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    public enum Type { DEPOSIT, WITHDRAWAL, INTEREST }

    private final String transactionId;
    private final String accountNumber;
    private final Type type;
    private final double amount;
    private final LocalDateTime timestamp;
    private final double balanceAfter;

    public Transaction(String accountNumber, Type type, double amount, double balanceAfter) {
        this.transactionId = UUID.randomUUID().toString();
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.balanceAfter = balanceAfter;
    }

    @Override
    public String toString() {
        return String.format("Transaction[%s] %s %.2f at %s | Balance After: %.2f",
                transactionId, type, amount, timestamp, balanceAfter);
    }
}
