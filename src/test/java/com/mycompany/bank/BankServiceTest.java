package com.mycompany.bank;

import com.mycompany.bank.exceptions.AccountOpeningException;
import com.mycompany.bank.exceptions.InsufficientFundsException;
import com.mycompany.bank.model.*;
import com.mycompany.bank.service.BankService;
import com.mycompany.bank.service.InMemoryBankService;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BankServiceTest {

    @Test
    public void testOpenInvestmentRequiresMinimumDeposit() {
        BankService bank = new InMemoryBankService();
        Customer customer = new Customer("Alice", "Kgosi", "Gaborone");
        try {
            bank.openInvestmentAccount(customer, "Main", new BigDecimal("400.00"));
            fail("Expected AccountOpeningException");
        } catch (AccountOpeningException expected) {
            // expected
        }
    }

    @Test
    public void testSavingsDoesNotAllowWithdrawal() {
        BankService bank = new InMemoryBankService();
        Customer customer = new Customer("Bob", "Dube", "Francistown");
        SavingsAccount savings = bank.openSavingsAccount(customer, "Main");
        bank.deposit(savings, new BigDecimal("1000.00"));
        try {
            bank.withdraw(savings, new BigDecimal("100.00"));
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException expected) {
            // expected
        }
    }

    @Test
    public void testApplyMonthlyInterest() {
        BankService bank = new InMemoryBankService();
        Customer customer = new Customer("Carl", "Molefe", "Molepolole");
        InvestmentAccount investment = bank.openInvestmentAccount(customer, "Main", new BigDecimal("1000.00"));
        SavingsAccount savings = bank.openSavingsAccount(customer, "Main");
        bank.deposit(savings, new BigDecimal("1000.00"));

        bank.applyMonthlyInterestToAllInterestBearingAccounts();

        assertEquals(new BigDecimal("1050.00"), investment.getBalance());
        assertEquals(new BigDecimal("1000.50"), savings.getBalance());
    }

    @Test
    public void testChequeRequiresEmploymentDetails() {
        BankService bank = new InMemoryBankService();
        Customer customer = new Customer("Dineo", "Phiri", "Lobatse");
        try {
            bank.openChequeAccount(customer, "Main", null);
            fail("Expected AccountOpeningException");
        } catch (NullPointerException | AccountOpeningException expected) {
            // expected
        }
        EmploymentDetails employment = new EmploymentDetails("Acme Ltd", "Plot 123, Gaborone");
        ChequeAccount cheque = bank.openChequeAccount(customer, "Main", employment);
        assertNotNull(cheque.getEmploymentDetails());
    }

    @Test
    public void testDepositAndWithdrawChequeAccount() {
        BankService bank = new InMemoryBankService();
        Customer customer = new Customer("Esi", "Moyo", "Maun");
        EmploymentDetails employment = new EmploymentDetails("Acme Ltd", "Plot 123, Gaborone");
        ChequeAccount cheque = bank.openChequeAccount(customer, "Main", employment);

        bank.deposit(cheque, new BigDecimal("1500.00"));
        assertEquals(new BigDecimal("1500.00"), cheque.getBalance());

        bank.withdraw(cheque, new BigDecimal("500.00"));
        assertEquals(new BigDecimal("1000.00"), cheque.getBalance());

        try {
            bank.withdraw(cheque, new BigDecimal("2000.00"));
            fail("Expected InsufficientFundsException");
        } catch (InsufficientFundsException expected) {
            // expected
        }
    }
}
