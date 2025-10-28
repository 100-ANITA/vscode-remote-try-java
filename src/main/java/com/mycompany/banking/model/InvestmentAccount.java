package com.mycompany.banking.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvestmentAccount extends Account implements InterestBearing {
    private static final BigDecimal MONTHLY_RATE = new BigDecimal("0.05"); // 5%

    public InvestmentAccount(String accountNumber, String branch, Customer owner, BigDecimal initialDeposit) {
        super(accountNumber, branch, owner);
        if (initialDeposit == null || initialDeposit.compareTo(new BigDecimal("500.00")) < 0) {
            throw new IllegalArgumentException("Investment account requires minimum opening deposit of BWP500.00");
        }
        super.deposit(initialDeposit);
    }

    @Override
    public BigDecimal getMonthlyInterestRate() {
        return MONTHLY_RATE;
    }

    @Override
    public BigDecimal calculateMonthlyInterest() {
        return getBalance().multiply(MONTHLY_RATE).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public void applyMonthlyInterest() {
        BigDecimal interest = calculateMonthlyInterest();
        if (interest.compareTo(BigDecimal.ZERO) > 0) {
            super.deposit(interest);
        }
    }
}
