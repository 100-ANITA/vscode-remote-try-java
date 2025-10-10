package com.mycompany.banking.service;

import com.mycompany.banking.model.*;

import java.math.BigDecimal;
import java.util.*;

public class Bank {
    private final String name;
    private final String branch;

    private final Map<String, Customer> customersById = new HashMap<>();
    private final Map<String, Account> accountsByNumber = new HashMap<>();

    public Bank(String name, String branch) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is required");
        }
        if (branch == null || branch.isEmpty()) {
            throw new IllegalArgumentException("branch is required");
        }
        this.name = name;
        this.branch = branch;
    }

    public Customer registerCustomer(String firstName, String surname, String address) {
        Customer customer = new Customer(firstName, surname, address);
        customersById.put(customer.getCustomerId(), customer);
        return customer;
    }

    public ChequeAccount openChequeAccount(Customer customer, String accountNumber) {
        requireCustomer(customer);
        if (customer.getEmploymentInfo() == null) {
            throw new IllegalArgumentException("Cheque account requires employment information");
        }
        ChequeAccount account = new ChequeAccount(accountNumber, branch, customer);
        linkAccount(customer, account);
        return account;
    }

    public InvestmentAccount openInvestmentAccount(Customer customer, String accountNumber, BigDecimal initialDeposit) {
        requireCustomer(customer);
        InvestmentAccount account = new InvestmentAccount(accountNumber, branch, customer, initialDeposit);
        linkAccount(customer, account);
        return account;
    }

    public SavingsAccount openSavingsAccount(Customer customer, String accountNumber) {
        requireCustomer(customer);
        SavingsAccount account = new SavingsAccount(accountNumber, branch, customer);
        linkAccount(customer, account);
        return account;
    }

    public void deposit(String accountNumber, BigDecimal amount) {
        accountByNumber(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, BigDecimal amount) {
        accountByNumber(accountNumber).withdraw(amount);
    }

    public void applyMonthlyInterest() {
        for (Account account : accountsByNumber.values()) {
            if (account instanceof InterestBearing) {
                ((InterestBearing) account).applyMonthlyInterest();
            }
        }
    }

    public Account getAccount(String accountNumber) {
        return accountByNumber(accountNumber);
    }

    public Optional<Customer> findCustomerById(String customerId) {
        return Optional.ofNullable(customersById.get(customerId));
    }

    private void linkAccount(Customer customer, Account account) {
        if (accountsByNumber.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("Duplicate account number");
        }
        accountsByNumber.put(account.getAccountNumber(), account);
        customer.addAccount(account);
    }

    private Account accountByNumber(String accountNumber) {
        Account account = accountsByNumber.get(accountNumber);
        if (account == null) {
            throw new NoSuchElementException("Account not found: " + accountNumber);
        }
        return account;
    }

    private void requireCustomer(Customer customer) {
        if (!customersById.containsKey(customer.getCustomerId())) {
            throw new IllegalArgumentException("Customer must be registered with the bank");
        }
    }
}
