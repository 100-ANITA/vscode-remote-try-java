package com.example.banking.model;

import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private final String firstName;
    private final String surname;
    private final String address;
    private final EmploymentInfo employmentInfo; // may be null when unemployed

    private Customer(UUID id, String firstName, String surname, String address, EmploymentInfo employmentInfo) {
        this.id = Objects.requireNonNull(id, "id");
        this.firstName = Objects.requireNonNull(firstName, "firstName");
        this.surname = Objects.requireNonNull(surname, "surname");
        this.address = Objects.requireNonNull(address, "address");
        this.employmentInfo = employmentInfo; // nullable
    }

    public static Customer create(String firstName, String surname, String address, EmploymentInfo employmentInfo) {
        return new Customer(UUID.randomUUID(), firstName, surname, address, employmentInfo);
    }

    public UUID getId() {
        return id;
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
}
