package com.mycompany.bank.model;

import java.math.BigDecimal;

public interface InterestBearing {
    BigDecimal getMonthlyInterestRate();
    void applyMonthlyInterest();
}
