package com.example.banking.model;

import com.example.banking.exception.InsufficientFundsException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvestmentAccount extends Account implements InterestBearing {
    public static final BigDecimal MONTHLY_RATE = new BigDecimal("0.05"); // 5%

    public InvestmentAccount(String branchCode, Customer holder, BigDecimal initialDeposit) {
        super(branchCode, holder);
        if (initialDeposit == null || initialDeposit.compareTo(new BigDecimal("500.00")) < 0) {
            throw new com.example.banking.exception.MinimumInitialDepositException(new BigDecimal("500.00"));
        }
        deposit(initialDeposit.setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public BigDecimal calculateMonthlyInterest() {
        return getBalance().multiply(MONTHLY_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void applyMonthlyInterest() {
        BigDecimal interest = calculateMonthlyInterest();
        if (interest.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(interest);
            this.transactions.add(new Transaction(TransactionType.INTEREST_CREDIT, interest, this.balance, "Monthly interest"));
        }
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
