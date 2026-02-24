package com.github.arcwinds.common;

import java.util.UUID;

public class Customer {
    private final UUID customerID;
    private final String name;
    private final String password;
    private final String email;
    private String creditCardNumber;
    
    public Customer(UUID customerID, String name, String password, String email, String creditCardNumber) {
        this.customerID = customerID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
    }

    
    public UUID getCustomerId() {
        return customerID;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getCreditCard() {
        return creditCardNumber;
    }

    public void setCreditCard(String creditCard) {
        this.creditCardNumber = creditCardNumber;
    }
}