package com.mycompany.banking.model;

import com.mycompany.banking.exception.InsufficientFundsException;
import com.mycompany.banking.exception.InvalidAmountException;
import com.mycompany.banking.exception.WithdrawalNotAllowedException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public abstract class Account {
    private final String accountNumber;
    private final String branch;
    private final Customer owner;
    private BigDecimal balance;

    protected Account(String accountNumber, String branch, Customer owner) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("accountNumber is required");
        }
        if (branch == null || branch.isEmpty()) {
            throw new IllegalArgumentException("branch is required");
        }
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.owner = Objects.requireNonNull(owner, "owner is required");
        this.balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
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
        ensurePositiveAmount(amount);
        balance = balance.add(amount).setScale(2, RoundingMode.HALF_EVEN);
    }

    // Overloaded convenience methods to demonstrate polymorphic behavior
    public void deposit(double amount) {
        deposit(BigDecimal.valueOf(amount));
    }

    public void withdraw(BigDecimal amount) {
        ensurePositiveAmount(amount);
        if (!supportsWithdrawal()) {
            throw new WithdrawalNotAllowedException("Withdrawals are not allowed for this account type");
        }
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        balance = balance.subtract(amount).setScale(2, RoundingMode.HALF_EVEN);
    }

    // Overloaded convenience method
    public void withdraw(double amount) {
        withdraw(BigDecimal.valueOf(amount));
    }

    protected boolean supportsWithdrawal() {
        return true;
    }

    protected void ensurePositiveAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }
    }
}
