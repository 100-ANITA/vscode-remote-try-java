package com.example.banking.model;

import com.example.banking.exception.InvalidAmountException;
import com.example.banking.exception.OperationNotAllowedException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class Account {
    private final String accountNumber;
    private final String branchCode;
    private final Customer holder;

    protected BigDecimal balance;
    protected final List<Transaction> transactions;

    protected Account(String branchCode, Customer holder) {
        this.accountNumber = generateAccountNumber();
        this.branchCode = Objects.requireNonNull(branchCode, "branchCode");
        this.holder = Objects.requireNonNull(holder, "holder");
        this.balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.transactions = new ArrayList<>();
    }

    private static String generateAccountNumber() {
        String raw = UUID.randomUUID().toString().replaceAll("-", "");
        return raw.substring(0, 12).toUpperCase();
    }

    public String getAccountNumber() { return accountNumber; }
    public String getBranchCode() { return branchCode; }
    public Customer getHolder() { return holder; }
    public BigDecimal getBalance() { return balance; }
    public List<Transaction> getTransactions() { return List.copyOf(transactions); }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        balance = balance.add(amount);
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount, balance, "Deposit"));
    }

    public void deposit(double amount) {
        deposit(BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP));
    }

    public void withdraw(BigDecimal amount) {
        throw new OperationNotAllowedException("Withdrawals are not allowed on this account");
    }
}
