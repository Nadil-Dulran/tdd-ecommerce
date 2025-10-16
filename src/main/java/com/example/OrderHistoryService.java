package com.example;

import java.util.List;

public class OrderHistoryService {

    private final InMemoryOrderRepository repo;

    public OrderHistoryService(InMemoryOrderRepository repo) {
        this.repo = repo;
    }

    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    public List<Order> getOrdersByUser(String userId) {
        return repo.findByUser(userId);
    }
}

