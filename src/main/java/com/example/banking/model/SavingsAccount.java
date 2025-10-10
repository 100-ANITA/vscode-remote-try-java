package com.example.banking.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SavingsAccount extends Account implements InterestBearing {
    public static final BigDecimal MONTHLY_RATE = new BigDecimal("0.0005"); // 0.05%

    public SavingsAccount(String branchCode, Customer holder) {
        super(branchCode, holder);
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
        throw new com.example.banking.exception.OperationNotAllowedException("Withdrawals are not allowed from SavingsAccount");
    }
}
