package com.example;

public record CheckoutResult(boolean success, double amount, String message) { }
