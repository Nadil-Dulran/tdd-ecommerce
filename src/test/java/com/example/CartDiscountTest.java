package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CartDiscountTest {

    @Test
    void bulkDiscount_appliesWhenQuantityOverThreshold() {
        // Arrange
        Catalog catalog = new Catalog();
        Product apple = new Product("SKU1", "Apple", 10);
        catalog.add(apple);

        Cart cart = new Cart(catalog);
        cart.add("SKU1", 10); // 10 items × 10 = 100

        // Apply a bulk discount rule (10% off when qty >= 10)
        DiscountRule rule = new BulkDiscountRule("SKU1", 10, 0.10);
        double discount = rule.calculateDiscount(cart);

        assertEquals(10.0, discount, 0.01, "Should apply 10% bulk discount");
    }

    @Test
    void orderDiscount_appliesWhenTotalExceedsThreshold() {
        Catalog catalog = new Catalog();
        catalog.add(new Product("A", "Laptop", 1200));

        Cart cart = new Cart(catalog);
        cart.add("A", 1);

        DiscountRule rule = new OrderDiscountRule(1000, 0.05); // 5% off
        double discount = rule.calculateDiscount(cart);

        assertEquals(60.0, discount, 0.01, "Should apply 5% discount on 1200 total");
    }
}
