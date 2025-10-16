package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Shopping Cart
 * - Requirement A/B: add, remove, total
 * - Requirement C: check inventory availability
 */
public class Cart {
    private final Catalog catalog;
    private final InventoryService inventory;
    private final Map<String, Integer> items = new HashMap<>();

    /**
     * Primary constructor — allows inventory checking.
     */
    public Cart(Catalog catalog, InventoryService inventory) {
        this.catalog = catalog;
        // Use a Null Object pattern: if no inventory is given, use a stub with "infinite" stock.
        this.inventory = (inventory != null) ? inventory : sku -> Integer.MAX_VALUE;
    }

    /**
     * Convenience constructor for earlier tests (no inventory check).
     */
    public Cart(Catalog catalog) {
        this(catalog, null);
    }

    /**
     * Adds a product to the cart.
     * Throws IllegalStateException if requested qty exceeds available stock.
     */
    public void add(String sku, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be positive");

        Product product = catalog.findBySku(sku);
        if (product == null) throw new IllegalArgumentException("Unknown SKU: " + sku);

        int available = inventory.getAvailable(sku);
        if (qty > available) {
            throw new IllegalStateException("Not enough stock for SKU: " + sku);
        }

        items.merge(sku, qty, Integer::sum);
    }

    /**
     * Removes a product completely from the cart.
     */
    public void remove(String sku) {
        items.remove(sku);
    }

    /**
     * Calculates total cart value.
     */
    public int total() {
        int sum = 0;
        for (var e : items.entrySet()) {
            Product p = catalog.findBySku(e.getKey());
            sum += p.getPrice() * e.getValue();
        }
        return sum;
    }
    
    public Catalog getCatalog() {
        return catalog;
    }

    public InventoryService getInventoryService() {
        return inventory;
    }

    public double totalWithDiscounts(DiscountRule... rules) {
    double discount = 0.0;
    for (DiscountRule r : rules) {
        discount += r.calculateDiscount(this);
    }
    return total() - discount;
}
    /** For testing/debugging convenience */
    public Map<String, Integer> getItems() {
        return Map.copyOf(items);
    }
}
