package com.elitebank.model;

public class ChequeAccount extends Account {
    private final String employerName;
    private final String employerAddress;

    public ChequeAccount(Customer owner, double initialDeposit, String employerName, String employerAddress) {
        super(owner, initialDeposit);
        if (employerName == null || employerName.isBlank())
            throw new IllegalArgumentException("Employer details required for Cheque Account.");
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds");
        balance -= amount;
    }

    public String getEmployerName() { return employerName; }
}
