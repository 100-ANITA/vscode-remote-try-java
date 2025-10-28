package com.mycompany.banking.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final String customerId;
    private final String firstName;
    private final String surname;
    private final String address;
    private EmploymentInfo employmentInfo;
    private final List<Account> accounts = new ArrayList<>();

    public Customer(String firstName, String surname, String address) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("firstName is required");
        }
        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("surname is required");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("address is required");
        }
        this.customerId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
    }

    public String getCustomerId() {
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

    public EmploymentInfo getEmploymentInfo() {
        return employmentInfo;
    }

    public void setEmploymentInfo(EmploymentInfo employmentInfo) {
        this.employmentInfo = Objects.requireNonNull(employmentInfo, "employmentInfo");
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void addAccount(Account account) {
        accounts.add(Objects.requireNonNull(account, "account"));
    }
}
