package com.elitebank;

import com.elitebank.model.*;
import com.elitebank.service.Bank;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        IndividualCustomer alice = bank.registerCustomer("Alice", "Moyo", "Gaborone", "ID1234", LocalDate.of(2000, 1, 15));
        CorporateCustomer acme = bank.registerCorporateCustomer("Acme Ltd", "John", "Doe", "Gaborone", "REG-9988");

        Account acc1 = bank.openInvestmentAccount(alice, 1000);
        Account acc2 = bank.openSavingsAccount(alice, 200);
        Account acc3 = bank.openChequeAccount(acme, 500, "Acme Ltd", "Industrial Rd");

        bank.deposit(acc1.getAccountNumber(), 200);
        bank.deposit(acc2.getAccountNumber(), 50, "Bonus");
        bank.applyMonthlyInterestAll();

        System.out.println("=== ACCOUNTS ===");
        bank.getAllAccounts().forEach(System.out::println);

        System.out.println("\n=== TRANSACTIONS ===");
        bank.getAllTransactions().forEach(System.out::println);
    }
}
