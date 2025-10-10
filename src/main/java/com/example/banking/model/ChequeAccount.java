package com.example.banking.model;

import com.example.banking.exception.InsufficientFundsException;

import java.math.BigDecimal;

public class ChequeAccount extends Account {
    public ChequeAccount(String branchCode, Customer holder) {
        super(branchCode, holder);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new com.example.banking.exception.InvalidAmountException("Withdrawal amount must be positive");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        this.balance = this.balance.subtract(amount);
        this.transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount, this.balance, "Withdrawal"));
    }
}
