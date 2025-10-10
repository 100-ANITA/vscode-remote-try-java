package com.example.banking.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final Instant timestamp;
    private final TransactionType type;
    private final BigDecimal amount;
    private final BigDecimal resultingBalance;
    private final String description;

    public Transaction(TransactionType type, BigDecimal amount, BigDecimal resultingBalance, String description) {
        this.id = UUID.randomUUID();
        this.timestamp = Instant.now();
        this.type = Objects.requireNonNull(type, "type");
        this.amount = Objects.requireNonNull(amount, "amount");
        this.resultingBalance = Objects.requireNonNull(resultingBalance, "resultingBalance");
        this.description = description == null ? "" : description;
    }

    public UUID getId() { return id; }
    public Instant getTimestamp() { return timestamp; }
    public TransactionType getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getResultingBalance() { return resultingBalance; }
    public String getDescription() { return description; }
}
