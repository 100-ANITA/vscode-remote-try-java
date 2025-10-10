package com.mycompany.banking.model;

import com.mycompany.banking.exception.WithdrawalNotAllowedException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends Account implements InterestBearing {
    private static final BigDecimal MONTHLY_RATE = new BigDecimal("0.0005"); // 0.05%

    public SavingsAccount(String accountNumber, String branch, Customer owner) {
        super(accountNumber, branch, owner);
    }

    @Override
    protected boolean supportsWithdrawal() {
        return false; // No withdrawals allowed
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
