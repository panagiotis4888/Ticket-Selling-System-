package com.github.arcwinds.common;

import java.util.UUID;

public class Customer {
    private final UUID customerID;
    private final String name;
    private final String password;
    private final String email;

    public Customer(UUID customerID, String name, String password, String email) {
        this.customerID = customerID;
        this.name = name;
        this.password = password;
        this.email = email;
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
}