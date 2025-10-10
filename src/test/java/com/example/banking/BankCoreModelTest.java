package com.example.banking;

import com.example.banking.exception.OperationNotAllowedException;
import com.example.banking.model.*;
import com.example.banking.service.Bank;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BankCoreModelTest {

    @Test
    void savings_deposit_and_no_withdrawal() {
        Bank bank = new Bank();
        Customer c = bank.registerCustomer("Alice", "Doe", "Gaborone", null);
        SavingsAccount sa = bank.openSavingsAccount(c, "001");
        bank.deposit(sa.getAccountNumber(), new BigDecimal("1000.00"));
        assertEquals(new BigDecimal("1000.00"), sa.getBalance());
        assertThrows(OperationNotAllowedException.class, () -> bank.withdraw(sa.getAccountNumber(), new BigDecimal("100.00")));
    }

    @Test
    void investment_requires_min_initial_and_accrues_interest() {
        Bank bank = new Bank();
        Customer c = bank.registerCustomer("Bob", "Smith", "Francistown", null);
        InvestmentAccount ia = bank.openInvestmentAccount(c, "001", new BigDecimal("500.00"));
        assertEquals(new BigDecimal("500.00"), ia.getBalance());
        bank.payMonthlyInterest();
        assertEquals(new BigDecimal("525.00"), ia.getBalance());
    }

    @Test
    void cheque_requires_employment_and_can_withdraw() {
        Bank bank = new Bank();
        EmploymentInfo emp = new EmploymentInfo("ACME", "Plot 1 Main Rd");
        Customer c = bank.registerCustomer("Carol", "Jones", "Maun", emp);
        ChequeAccount ca = bank.openChequeAccount(c, "002");
        bank.deposit(ca.getAccountNumber(), new BigDecimal("300.00"));
        bank.withdraw(ca.getAccountNumber(), new BigDecimal("100.00"));
        assertEquals(new BigDecimal("200.00"), ca.getBalance());
    }
}
