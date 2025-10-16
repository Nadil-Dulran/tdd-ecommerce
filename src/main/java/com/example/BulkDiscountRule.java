package com.example;

public class BulkDiscountRule implements DiscountRule {
    private final String sku;
    private final int minQuantity;
    private final double rate;

    public BulkDiscountRule(String sku, int minQuantity, double rate) {
        this.sku = sku;
        this.minQuantity = minQuantity;
        this.rate = rate;
    }

    @Override
    public double calculateDiscount(Cart cart) {
        Integer qty = cart.getItems().get(sku);
        if (qty == null || qty < minQuantity) return 0.0;

        Product product = cart.getCatalog().findBySku(sku);
        return product.getPrice() * qty * rate;
    }
}
