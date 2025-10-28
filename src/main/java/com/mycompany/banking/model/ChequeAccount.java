package com.mycompany.banking.model;

import java.math.BigDecimal;

public class ChequeAccount extends Account {
    public ChequeAccount(String accountNumber, String branch, Customer owner) {
        super(accountNumber, branch, owner);
    }

    @Override
    protected boolean supportsWithdrawal() {
        return true; // deposits and withdrawals allowed
    }
}
