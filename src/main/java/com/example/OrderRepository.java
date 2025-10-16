package com.example;

import java.util.List;

public interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
    List<Order> findByUser(String userId);
}
