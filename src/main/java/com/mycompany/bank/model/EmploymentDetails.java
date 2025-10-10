package com.mycompany.bank.model;

import java.util.Objects;

public class EmploymentDetails {
    private final String companyName;
    private final String companyAddress;

    public EmploymentDetails(String companyName, String companyAddress) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public boolean isValid() {
        return companyName != null && !companyName.isBlank() &&
               companyAddress != null && !companyAddress.isBlank();
    }

    @Override
    public String toString() {
        return "EmploymentDetails{" +
                "companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                '}';
    }
}
