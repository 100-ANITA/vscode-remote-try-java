/*----------------------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *---------------------------------------------------------------------------------------*/

package com.mycompany.app;

import com.mycompany.bank.model.Customer;
import com.mycompany.bank.model.EmploymentDetails;
import com.mycompany.bank.model.InvestmentAccount;
import com.mycompany.bank.model.SavingsAccount;
import com.mycompany.bank.service.BankService;
import com.mycompany.bank.service.InMemoryBankService;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        BankService bank = new InMemoryBankService();

        Customer customer = new Customer("Thato", "Nkala", "Gaborone");

        // Open accounts
        SavingsAccount savings = bank.openSavingsAccount(customer, "Gaborone Main");
        InvestmentAccount investment = bank.openInvestmentAccount(customer, "Gaborone Main", new BigDecimal("1000.00"));

        // Cheque with employment details
        EmploymentDetails employment = new EmploymentDetails("Botswana Telecoms", "Plot 42, Gaborone");
        bank.openChequeAccount(customer, "Gaborone Main", employment);

        // Transactions
        bank.deposit(savings, new BigDecimal("500.00"));
        bank.deposit(investment, new BigDecimal("250.00"));

        // Apply monthly interest
        bank.applyMonthlyInterestToAllInterestBearingAccounts();

        System.out.println("Savings balance: " + savings.getBalance());
        System.out.println("Investment balance: " + investment.getBalance());
    }
}
