package com.elitebank.model;

import java.time.LocalDate;
import java.util.UUID;

public class IndividualCustomer extends Customer {
    private final String nationalId;
    private final LocalDate dateOfBirth;

    public IndividualCustomer(String firstName, String lastName, String address, String nationalId, LocalDate dateOfBirth) {
        super(UUID.randomUUID().toString(), firstName, lastName, address);
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalId() { return nationalId; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }

    @Override
    public String getCustomerType() {
        return "Individual";
    }
}
