package com.elitebank.model;

import java.util.UUID;

public class CorporateCustomer extends Customer {
    private final String companyName;
    private final String companyRegistration;

    public CorporateCustomer(String companyName, String contactFirstName, String contactLastName, String address, String companyRegistration) {
        super(UUID.randomUUID().toString(), contactFirstName, contactLastName, address);
        this.companyName = companyName;
        this.companyRegistration = companyRegistration;
    }

    public String getCompanyName() { return companyName; }
    public String getCompanyRegistration() { return companyRegistration; }

    @Override
    public String getCustomerType() {
        return "Corporate";
    }
}
