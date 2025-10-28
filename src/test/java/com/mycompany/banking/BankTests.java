package com.mycompany.banking;

import com.mycompany.banking.exception.InsufficientFundsException;
import com.mycompany.banking.exception.WithdrawalNotAllowedException;
import com.mycompany.banking.model.*;
import com.mycompany.banking.service.Bank;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BankTests {

    @Test
    public void customerCanOpenMultipleAccounts() {
        Bank bank = new Bank("ABC Bank", "Gaborone");
        Customer john = bank.registerCustomer("John", "Doe", "123 Main");
        john.setEmploymentInfo(new EmploymentInfo("Acme", "Plot 1"));

        SavingsAccount savings = bank.openSavingsAccount(john, "SAV-001");
        InvestmentAccount investment = bank.openInvestmentAccount(john, "INV-001", new BigDecimal("500.00"));
        ChequeAccount cheque = bank.openChequeAccount(john, "CHQ-001");

        assertEquals(3, john.getAccounts().size());
        assertSame(savings, bank.getAccount("SAV-001"));
        assertSame(investment, bank.getAccount("INV-001"));
        assertSame(cheque, bank.getAccount("CHQ-001"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void investmentRequiresMinimumOpeningDeposit() {
        Bank bank = new Bank("ABC Bank", "Gaborone");
        Customer jane = bank.registerCustomer("Jane", "Doe", "456 Road");
        bank.openInvestmentAccount(jane, "INV-002", new BigDecimal("499.99"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void chequeRequiresEmploymentInfo() {
        Bank bank = new Bank("ABC Bank", "Gaborone");
        Customer jane = bank.registerCustomer("Jane", "Doe", "456 Road");
        bank.openChequeAccount(jane, "CHQ-002");
    }

    @Test
    public void depositAndWithdrawCheque() {
        Bank bank = new Bank("ABC Bank", "Gaborone");
        Customer john = bank.registerCustomer("John", "Doe", "123 Main");
        john.setEmploymentInfo(new EmploymentInfo("Acme", "Plot 1"));
        ChequeAccount cheque = bank.openChequeAccount(john, "CHQ-003");

        bank.deposit("CHQ-003", new BigDecimal("1000.00"));
        assertEquals(new BigDecimal("1000.00"), cheque.getBalance());

        bank.withdraw("CHQ-003", new BigDecimal("400.00"));
        assertEquals(new BigDecimal("600.00"), cheque.getBalance());
    }

    @Test(expected = InsufficientFundsException.class)
    public void cannotOverdrawCheque() {
        Bank bank = new Bank("ABC Bank", "Gaborone");
        Customer john = bank.registerCustomer("John", "Doe", "123 Main");
        john.setEmploymentInfo(new EmploymentInfo("Acme", "Plot 1"));
        ChequeAccount cheque = bank.openChequeAccount(john, "CHQ-004");

        bank.deposit("CHQ-004", new BigDecimal("100.00"));
        bank.withdraw("CHQ-004", new BigDecimal("150.00"));
    }

    @Test(expected = WithdrawalNotAllowedException.class)
    public void savingsCannotWithdraw() {
        Bank bank = new Bank("ABC Bank", "Gaborone");
        Customer john = bank.registerCustomer("John", "Doe", "123 Main");
        SavingsAccount savings = bank.openSavingsAccount(john, "SAV-002");

        bank.deposit("SAV-002", new BigDecimal("200.00"));
        bank.withdraw("SAV-002", new BigDecimal("50.00"));
    }

    @Test
    public void interestAppliedCorrectly() {
        Bank bank = new Bank("ABC Bank", "Gaborone");
        Customer john = bank.registerCustomer("John", "Doe", "123 Main");
        john.setEmploymentInfo(new EmploymentInfo("Acme", "Plot 1"));
        SavingsAccount savings = bank.openSavingsAccount(john, "SAV-003");
        InvestmentAccount investment = bank.openInvestmentAccount(john, "INV-003", new BigDecimal("500.00"));
        ChequeAccount cheque = bank.openChequeAccount(john, "CHQ-005");

        bank.deposit("SAV-003", new BigDecimal("1000.00")); // 0.05% of 1000 = 0.50
        bank.deposit("CHQ-005", new BigDecimal("1000.00")); // no interest

        bank.applyMonthlyInterest();

        assertEquals(new BigDecimal("1000.50"), savings.getBalance());
        // Investment account had 500.00, 5% interest = 25.00
        assertEquals(new BigDecimal("525.00"), investment.getBalance());
        assertEquals(new BigDecimal("1000.00"), cheque.getBalance());
    }
}
