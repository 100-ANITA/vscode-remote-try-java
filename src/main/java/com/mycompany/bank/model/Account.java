package com.mycompany.bank.model;

import com.mycompany.bank.exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public abstract class Account {
    private final String accountNumber;
    private final String branch;
    private final Customer owner;
    private BigDecimal balance;

    protected Account(String accountNumber, Customer owner, String branch) {
        this.accountNumber = Objects.requireNonNull(accountNumber, "accountNumber");
        this.owner = Objects.requireNonNull(owner, "owner");
        this.branch = Objects.requireNonNull(branch, "branch");
        this.balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBranch() {
        return branch;
    }

    public Customer getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        deposit(amount, null);
    }

    public void deposit(BigDecimal amount, String reference) {
        BigDecimal normalized = normalizeAmount(amount);
        ensurePositive(normalized);
        this.balance = this.balance.add(normalized);
    }

    public void withdraw(BigDecimal amount) {
        withdraw(amount, null);
    }

    public void withdraw(BigDecimal amount, String reference) {
        BigDecimal normalized = normalizeAmount(amount);
        ensurePositive(normalized);
        if (this.balance.compareTo(normalized) < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        this.balance = this.balance.subtract(normalized);
    }

    protected void applyInterestAmount(BigDecimal interestAmount) {
        if (interestAmount == null) {
            return;
        }
        BigDecimal normalized = interestAmount.setScale(2, RoundingMode.HALF_UP);
        if (normalized.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(normalized);
        }
    }

    private static BigDecimal normalizeAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    private static void ensurePositive(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", branch='" + branch + '\'' +
                ", owner=" + owner.getFullName() +
                ", balance=" + balance +
                '}';
    }
}
