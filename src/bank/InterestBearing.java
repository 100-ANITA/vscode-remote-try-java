package com.elitebank.model;

public interface InterestBearing {
    double calculateMonthlyInterest(); // returns interest amount

    default void applyMonthlyInterest() {
        // default implementation (optional override)
    }
}
