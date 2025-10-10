package com.elitebank.model;

public abstract class Customer {
    protected final String customerId;
    protected final String firstName;
    protected final String lastName;
    protected final String address;

    public Customer(String customerId, String firstName, String lastName, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public abstract String getCustomerType(); // must be implemented by subclasses
}
