package com.mycompany.bank.model;

import com.mycompany.bank.exceptions.AccountOpeningException;

import java.util.Objects;

public class ChequeAccount extends Account {
    private final EmploymentDetails employmentDetails;

    public ChequeAccount(String accountNumber, Customer owner, String branch, EmploymentDetails employmentDetails) {
        super(accountNumber, owner, branch);
        this.employmentDetails = Objects.requireNonNull(employmentDetails, "employmentDetails");
        if (!employmentDetails.isValid()) {
            throw new AccountOpeningException("Employment details are required to open a ChequeAccount");
        }
    }

    public EmploymentDetails getEmploymentDetails() {
        return employmentDetails;
    }
}
