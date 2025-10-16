package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    @Test
    void checkout_success_createsOrderAndChargesPayment() {
        // Arrange
        Catalog catalog = new Catalog();
        Product laptop = new Product("L1", "Laptop", 1000);
        catalog.add(laptop);

        InventoryService inventory = Mockito.mock(InventoryService.class);
        Mockito.when(inventory.getAvailable("L1")).thenReturn(5);

        PaymentGateway gateway = Mockito.mock(PaymentGateway.class);
        Mockito.when(gateway.charge(950.0, "tok_123")).thenReturn(true); // includes discount

        OrderRepository repo = Mockito.mock(OrderRepository.class);

        Cart cart = new Cart(catalog, inventory);
        cart.add("L1", 1);

        DiscountRule orderDiscount = new OrderDiscountRule(900, 0.05);
        CheckoutService checkout = new CheckoutService(inventory, gateway, repo);

        // Act
        CheckoutResult result = checkout.checkout(cart, "tok_123", orderDiscount);

        // Assert
        assertTrue(result.success());
        assertEquals(950.0, result.amount(), 0.01);
        Mockito.verify(gateway).charge(950.0, "tok_123");
        Mockito.verify(repo).save(Mockito.any(Order.class));
    }

    @Test
    void checkout_paymentFailure_returnsErrorAndDoesNotSaveOrder() {
        Catalog catalog = new Catalog();
        Product phone = new Product("P1", "Phone", 800);
        catalog.add(phone);

        InventoryService inventory = Mockito.mock(InventoryService.class);
        Mockito.when(inventory.getAvailable("P1")).thenReturn(3);

        PaymentGateway gateway = Mockito.mock(PaymentGateway.class);
        Mockito.when(gateway.charge(800.0, "tok_fail")).thenReturn(false);

        OrderRepository repo = Mockito.mock(OrderRepository.class);

        Cart cart = new Cart(catalog, inventory);
        cart.add("P1", 1);

        CheckoutService checkout = new CheckoutService(inventory, gateway, repo);
        CheckoutResult result = checkout.checkout(cart, "tok_fail");

        assertFalse(result.success());
        Mockito.verify(repo, Mockito.never()).save(Mockito.any());
    }
}
