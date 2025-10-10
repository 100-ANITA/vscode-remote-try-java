package com.elitebank.service;

import com.elitebank.model.*;
import java.time.LocalDate;
import java.util.*;

public class Bank {
    private final Map<String, Customer> customers = new HashMap<>();
    private final Map<String, Account> accounts = new HashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();

    // Register an individual customer
    public IndividualCustomer registerCustomer(String firstName, String lastName, String address, String nationalId, LocalDate dob) {
        IndividualCustomer customer = new IndividualCustomer(firstName, lastName, address, nationalId, dob);
        customers.put(customer.getCustomerId(), customer);
        return customer;
    }

    // Register a corporate customer
    public CorporateCustomer registerCorporateCustomer(String companyName, String contactFirstName, String contactLastName, String address, String companyReg) {
        CorporateCustomer customer = new CorporateCustomer(companyName, contactFirstName, contactLastName, address, companyReg);
        customers.put(customer.getCustomerId(), customer);
        return customer;
    }

    // Open accounts
    public Account openSavingsAccount(Customer c, double initialDeposit) {
        Account account = new SavingsAccount(c, initialDeposit);
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public Account openInvestmentAccount(Customer c, double initialDeposit) {
        Account account = new InvestmentAccount(c, initialDeposit);
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    public Account openChequeAccount(Customer c, double initialDeposit, String employerName, String employerAddress) {
        Account account = new ChequeAccount(c, initialDeposit, employerName, employerAddress);
        accounts.put(account.getAccountNumber(), account);
        return account;
    }

    // Deposit (Overloaded)
    public void deposit(String accountNumber, double amount) {
        Account a = accounts.get(accountNumber);
        a.deposit(amount);
        transactions.add(new Transaction(accountNumber, Transaction.Type.DEPOSIT, amount, a.getBalance()));
    }

    public void deposit(String accountNumber, double amount, String note) {
        Account a = accounts.get(accountNumber);
        a.deposit(amount, note);
        transactions.add(new Transaction(accountNumber, Transaction.Type.DEPOSIT, amount, a.getBalance()));
    }

    // Withdraw
    public void withdraw(String accountNumber, double amount) {
        Account a = accounts.get(accountNumber);
        a.withdraw(amount);
        transactions.add(new Transaction(accountNumber, Transaction.Type.WITHDRAWAL, amount, a.getBalance()));
    }

    // Apply monthly interest to all eligible accounts
    public void applyMonthlyInterestAll() {
        for (Account a : accounts.values()) {
            if (a instanceof InterestBearing ib) {
                double before = a.getBalance();
                ib.applyMonthlyInterest();
                double after = a.getBalance();
                if (after > before) {
                    transactions.add(new Transaction(a.getAccountNumber(), Transaction.Type.INTEREST, after - before, after));
                }
            }
        }
    }

    public Collection<Account> getAllAccounts() { return accounts.values(); }
    public List<Transaction> getAllTransactions() { return List.copyOf(transactions); }
}
