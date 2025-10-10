package com.example.banking.service;

import com.example.banking.exception.AccountNotFoundException;
import com.example.banking.exception.EmploymentInformationRequiredException;
import com.example.banking.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Bank {
    private final Map<String, Account> accountNumberToAccount = new HashMap<>();
    private final Map<UUID, Customer> idToCustomer = new HashMap<>();

    public Customer registerCustomer(String firstName, String surname, String address, EmploymentInfo employment) {
        Customer customer = Customer.create(firstName, surname, address, employment);
        idToCustomer.put(customer.getId(), customer);
        return customer;
    }

    public SavingsAccount openSavingsAccount(Customer customer, String branch) {
        SavingsAccount acc = new SavingsAccount(branch, customer);
        accountNumberToAccount.put(acc.getAccountNumber(), acc);
        return acc;
    }

    public InvestmentAccount openInvestmentAccount(Customer customer, String branch, BigDecimal initialDeposit) {
        InvestmentAccount acc = new InvestmentAccount(branch, customer, initialDeposit);
        accountNumberToAccount.put(acc.getAccountNumber(), acc);
        return acc;
    }

    public ChequeAccount openChequeAccount(Customer customer, String branch) {
        if (customer.getEmploymentInfo() == null) {
            throw new EmploymentInformationRequiredException("Employment information required for ChequeAccount");
        }
        ChequeAccount acc = new ChequeAccount(branch, customer);
        accountNumberToAccount.put(acc.getAccountNumber(), acc);
        return acc;
    }

    public void deposit(String accountNumber, BigDecimal amount) {
        Account acc = findAccountOrThrow(accountNumber);
        acc.deposit(amount);
    }

    public void withdraw(String accountNumber, BigDecimal amount) {
        Account acc = findAccountOrThrow(accountNumber);
        acc.withdraw(amount);
    }

    public void payMonthlyInterest() {
        accountNumberToAccount.values().forEach(acc -> {
            if (acc instanceof InterestBearing ib) {
                ib.applyMonthlyInterest();
            }
        });
    }

    public List<Account> getAccounts(Customer customer) {
        return accountNumberToAccount.values().stream()
                .filter(a -> a.getHolder().getId().equals(customer.getId()))
                .collect(Collectors.toList());
    }

    public Account getAccount(String accountNumber) {
        return findAccountOrThrow(accountNumber);
    }

    private Account findAccountOrThrow(String accountNumber) {
        Account acc = accountNumberToAccount.get(accountNumber);
        if (acc == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return acc;
    }
}
