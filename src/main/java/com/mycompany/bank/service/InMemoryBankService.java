package com.mycompany.bank.service;

import com.mycompany.bank.model.*;
import com.mycompany.bank.util.AccountNumberGenerator;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBankService implements BankService {
    private final Map<String, Account> accountsByNumber = new ConcurrentHashMap<>();
    private final AccountNumberGenerator accountNumberGenerator;

    public InMemoryBankService() {
        this(new AccountNumberGenerator());
    }

    public InMemoryBankService(AccountNumberGenerator accountNumberGenerator) {
        this.accountNumberGenerator = accountNumberGenerator;
    }

    @Override
    public SavingsAccount openSavingsAccount(Customer owner, String branch) {
        String accountNumber = accountNumberGenerator.next();
        SavingsAccount account = new SavingsAccount(accountNumber, owner, branch);
        registerAccount(owner, account);
        return account;
    }

    @Override
    public InvestmentAccount openInvestmentAccount(Customer owner, String branch, BigDecimal initialDeposit) {
        String accountNumber = accountNumberGenerator.next();
        InvestmentAccount account = new InvestmentAccount(accountNumber, owner, branch, initialDeposit);
        registerAccount(owner, account);
        return account;
    }

    @Override
    public ChequeAccount openChequeAccount(Customer owner, String branch, EmploymentDetails employmentDetails) {
        String accountNumber = accountNumberGenerator.next();
        ChequeAccount account = new ChequeAccount(accountNumber, owner, branch, employmentDetails);
        registerAccount(owner, account);
        return account;
    }

    @Override
    public void deposit(Account account, BigDecimal amount) {
        Objects.requireNonNull(account, "account").deposit(amount);
    }

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {
        getRequiredAccount(accountNumber).deposit(amount);
    }

    @Override
    public void withdraw(Account account, BigDecimal amount) {
        Objects.requireNonNull(account, "account").withdraw(amount);
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {
        getRequiredAccount(accountNumber).withdraw(amount);
    }

    @Override
    public Optional<Account> findAccount(String accountNumber) {
        return Optional.ofNullable(accountsByNumber.get(accountNumber));
    }

    @Override
    public void applyMonthlyInterestToAllInterestBearingAccounts() {
        accountsByNumber.values().forEach(account -> {
            if (account instanceof InterestBearing) {
                ((InterestBearing) account).applyMonthlyInterest();
            }
        });
    }

    private void registerAccount(Customer owner, Account account) {
        accountsByNumber.put(account.getAccountNumber(), account);
        owner.addAccount(account);
    }

    private Account getRequiredAccount(String accountNumber) {
        return findAccount(accountNumber).orElseThrow(() -> new NoSuchElementException("Account not found: " + accountNumber));
    }
}
