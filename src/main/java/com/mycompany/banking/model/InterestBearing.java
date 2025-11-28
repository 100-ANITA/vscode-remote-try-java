package com.mycompany.banking.model;

import java.math.BigDecimal;

public interface InterestBearing {
    BigDecimal getMonthlyInterestRate();

    BigDecimal calculateMonthlyInterest();

    void applyMonthlyInterest();
}
