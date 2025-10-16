package com.example;

import java.time.LocalDateTime;
import java.util.Map;

public class Order {
    private final Map<String,Integer> items;
    private final double amount;
    private final LocalDateTime createdAt = LocalDateTime.now();

    public Order(Map<String,Integer> items, double amount) {
        this.items = items;
        this.amount = amount;
    }

    public double getAmount() { return amount; }
    public Map<String,Integer> getItems() { return items; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
