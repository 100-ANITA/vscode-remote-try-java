package com.mycompany.bank.util;

import java.util.concurrent.atomic.AtomicLong;

public class AccountNumberGenerator {
    private static final String PREFIX = "ACC";
    private final AtomicLong counter = new AtomicLong(1000000000L);

    public String next() {
        long next = counter.getAndIncrement();
        return PREFIX + "-" + next;
    }
}
