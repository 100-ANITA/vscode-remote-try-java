package com.example.banking.model;

import java.util.Objects;

public class EmploymentInfo {
    private final String companyName;
    private final String companyAddress;

    public EmploymentInfo(String companyName, String companyAddress) {
        this.companyName = Objects.requireNonNull(companyName, "companyName");
        this.companyAddress = Objects.requireNonNull(companyAddress, "companyAddress");
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }
}
