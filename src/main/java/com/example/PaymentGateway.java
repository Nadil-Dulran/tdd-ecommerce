package com.example;

public interface PaymentGateway {
    boolean charge(double amount, String token);
}
