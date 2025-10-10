package com.mycompany.bank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends Account implements InterestBearing {
    private static final BigDecimal MONTHLY_RATE = new BigDecimal("0.0005"); // 0.05%

    public SavingsAccount(String accountNumber, Customer owner, String branch) {
        super(accountNumber, owner, branch);
    }

    @Override
    public void withdraw(BigDecimal amount, String reference) {
        throw new UnsupportedOperationException("Withdrawals are not permitted from a SavingsAccount");
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
