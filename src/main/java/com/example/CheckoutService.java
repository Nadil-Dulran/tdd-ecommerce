package com.example;

public class CheckoutService {

    private final InventoryService inventory;
    private final PaymentGateway gateway;
    private final OrderRepository orders;

    public CheckoutService(InventoryService inventory, PaymentGateway gateway, OrderRepository orders) {
        this.inventory = inventory;
        this.gateway = gateway;
        this.orders = orders;
    }

    public CheckoutResult checkout(Cart cart, String token, DiscountRule... rules) {
        if (cart.getItems().isEmpty()) {
            return new CheckoutResult(false, 0.0, "Cart is empty");
        }

        // Validate inventory
        for (var e : cart.getItems().entrySet()) {
            int available = inventory.getAvailable(e.getKey());
            if (e.getValue() > available) {
                return new CheckoutResult(false, 0.0,
                        "Insufficient stock for " + e.getKey());
            }
        }

        // Compute total after discounts
        double total = cart.total();
        double discount = 0.0;
        for (DiscountRule r : rules) discount += r.calculateDiscount(cart);
        double amount = total - discount;

        // Attempt payment
        if (!gateway.charge(amount, token)) {
            return new CheckoutResult(false, amount, "Payment failed — no order created");
        }

        // Record order
        orders.save(new Order(cart.getItems(), amount));
        return new CheckoutResult(true, amount, "Order completed successfully");
    }

    public CheckoutResult checkout(Cart cart, String token) {
        return checkout(cart, token, new DiscountRule[0]);
    }
}
