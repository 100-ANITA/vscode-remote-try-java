package com.mycompany.bank.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final UUID customerId;
    private final String firstName;
    private final String surname;
    private final String address;
    private final List<Account> accounts;

    public Customer(String firstName, String surname, String address) {
        this.customerId = UUID.randomUUID();
        this.firstName = Objects.requireNonNull(firstName, "firstName");
        this.surname = Objects.requireNonNull(surname, "surname");
        this.address = Objects.requireNonNull(address, "address");
        this.accounts = new ArrayList<>();
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getFullName() {
        return firstName + " " + surname;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void addAccount(Account account) {
        this.accounts.add(Objects.requireNonNull(account, "account"));
    }
}
