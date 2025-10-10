package com.elitebank.model;

public class InvestmentAccount extends Account implements InterestBearing {
    private static final double MONTHLY_RATE = 0.05; // 5% monthly
    public static final double MIN_INITIAL_DEPOSIT = 500.0;

    public InvestmentAccount(Customer owner, double initialDeposit) {
        super(owner, initialDeposit);
        if (initialDeposit < MIN_INITIAL_DEPOSIT) {
            throw new IllegalArgumentException("Initial deposit must be at least " + MIN_INITIAL_DEPOSIT);
        }
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
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds");
        balance -= amount;
    }
}
