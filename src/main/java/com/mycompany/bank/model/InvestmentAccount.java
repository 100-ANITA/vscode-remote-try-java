package com.mycompany.bank.model;

import com.mycompany.bank.exceptions.AccountOpeningException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvestmentAccount extends Account implements InterestBearing {
    private static final BigDecimal MONTHLY_RATE = new BigDecimal("0.05"); // 5%
    private static final BigDecimal MIN_OPENING_DEPOSIT = new BigDecimal("500.00");

    public InvestmentAccount(String accountNumber, Customer owner, String branch, BigDecimal initialDeposit) {
        super(accountNumber, owner, branch);
        BigDecimal normalized = initialDeposit == null ? BigDecimal.ZERO : initialDeposit.setScale(2, RoundingMode.HALF_UP);
        if (normalized.compareTo(MIN_OPENING_DEPOSIT) < 0) {
            throw new AccountOpeningException("InvestmentAccount requires a minimum opening deposit of BWP500.00");
        }
        deposit(normalized, "Initial Deposit");
    }

    @Override
    public BigDecimal getMonthlyInterestRate() {
        return MONTHLY_RATE;
    }

    @Override
    public void applyMonthlyInterest() {
        BigDecimal interest = getBalance().multiply(getMonthlyInterestRate()).setScale(2, RoundingMode.HALF_UP);
        applyInterestAmount(interest);
    }
}
