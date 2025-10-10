package com.mycompany.bank.service;

import com.mycompany.bank.model.*;

import java.math.BigDecimal;
import java.util.Optional;

public interface BankService {
    SavingsAccount openSavingsAccount(Customer owner, String branch);
    InvestmentAccount openInvestmentAccount(Customer owner, String branch, BigDecimal initialDeposit);
    ChequeAccount openChequeAccount(Customer owner, String branch, EmploymentDetails employmentDetails);

    void deposit(Account account, BigDecimal amount);
    void deposit(String accountNumber, BigDecimal amount);

    void withdraw(Account account, BigDecimal amount);
    void withdraw(String accountNumber, BigDecimal amount);

    Optional<Account> findAccount(String accountNumber);

    void applyMonthlyInterestToAllInterestBearingAccounts();
}
