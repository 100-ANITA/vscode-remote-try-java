package com.elitebank.model;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Account {
    protected final String accountNumber;
    protected double balance;
    protected final Customer owner;
    protected final LocalDate openedDate;

    public Account(Customer owner, double initialDeposit) {
        this.accountNumber = UUID.randomUUID().toString();
        this.owner = owner;
        this.openedDate = LocalDate.now();
        this.balance = initialDeposit;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public Customer getOwner() { return owner; }

    // Basic deposit method
    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than 0");
        balance += amount;
    }

    // Overloaded deposit method with note (Overloading)
    public void deposit(double amount, String note) {
        deposit(amount);
        System.out.println("Deposit Note: " + note);
    }

    // Default withdraw (Overridden in subclasses)
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals not allowed for this account type.");
    }

    @Override
    public String toString() {
        return String.format("%s [Acc#: %s | Balance: %.2f | Owner: %s (%s)]",
                getClass().getSimpleName(), accountNumber, balance,
                owner.getFullName(), owner.getCustomerType());
    }
}
