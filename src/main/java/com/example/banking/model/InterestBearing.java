package com.example.banking.model;

import java.math.BigDecimal;

public interface InterestBearing {
    BigDecimal calculateMonthlyInterest();
    void applyMonthlyInterest();
}
