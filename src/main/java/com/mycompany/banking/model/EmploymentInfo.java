package com.mycompany.banking.model;

import java.util.Objects;

public class EmploymentInfo {
    private final String companyName;
    private final String companyAddress;

    public EmploymentInfo(String companyName, String companyAddress) {
        if (companyName == null || companyName.isEmpty()) {
            throw new IllegalArgumentException("companyName is required");
        }
        if (companyAddress == null || companyAddress.isEmpty()) {
            throw new IllegalArgumentException("companyAddress is required");
        }
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }
}
