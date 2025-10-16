package com.example;

import java.time.LocalDateTime;
import java.util.Map;

public class Order {
    private final String userId;
    private final Map<String,Integer> items;
    private final double amount;
    private final LocalDateTime createdAt = LocalDateTime.now();

    public Order(String userId, Map<String,Integer> items, double amount) {
        this.userId = userId;
        this.items = items;
        this.amount = amount;
    }

    // existing constructor (backward compatible)
    public Order(Map<String,Integer> items, double amount) {
        this("guest", items, amount);
    }

    public String getUserId() { return userId; }
    public double getAmount() { return amount; }
    public Map<String,Integer> getItems() { return items; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}