package com.example;

/**
 * Strategy interface for applying discounts to a cart.
 * Each rule can decide if and how it applies.
 */
@FunctionalInterface
public interface DiscountRule {
    double calculateDiscount(Cart cart);
}
