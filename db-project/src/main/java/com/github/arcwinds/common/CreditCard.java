package com.github.arcwinds.common;

import java.time.LocalDate;

public class CreditCard {
    private final String cardNumber;
    private final LocalDate expirationDate;
    private final String cvv;
    private int balance;

    public CreditCard(String cardNumber, LocalDate expirationDate, String cvv, int balance) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int newBalance) {
        this.balance = newBalance;
    }
}
