package com.elitebank.model;

public class SavingsAccount extends Account implements InterestBearing {
    private static final double MONTHLY_RATE = 0.0005; // 0.05% monthly

    public SavingsAccount(Customer owner, double initialDeposit) {
        super(owner, initialDeposit);
    }

    @Override
    public double calculateMonthlyInterest() {
        return balance * MONTHLY_RATE;
    }

    @Override
    public void applyMonthlyInterest() {
        double interest = calculateMonthlyInterest();
        deposit(interest);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals not allowed on Savings Account.");
    }
}
