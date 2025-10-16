package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

class OrderHistoryServiceTest {

    @Test
    void getAllOrders_returnsAllSavedOrders() {
        InMemoryOrderRepository repo = new InMemoryOrderRepository();
        repo.save(new Order(Map.of("A", 1), 100.0));
        repo.save(new Order(Map.of("B", 2), 200.0));

        OrderHistoryService history = new OrderHistoryService(repo);

        List<Order> orders = history.getAllOrders();
        assertEquals(2, orders.size());
    }

    @Test
    void getOrdersByUser_returnsOnlyThatUsersOrders() {
        InMemoryOrderRepository repo = new InMemoryOrderRepository();
        repo.save(new Order("user1", Map.of("A", 1), 100.0));
        repo.save(new Order("user2", Map.of("B", 2), 200.0));
        repo.save(new Order("user1", Map.of("C", 3), 300.0));

        OrderHistoryService history = new OrderHistoryService(repo);

        List<Order> user1Orders = history.getOrdersByUser("user1");
        assertEquals(2, user1Orders.size());
    }
}
