package com.example;

public class OrderDiscountRule implements DiscountRule {
    private final int thresholdTotal;
    private final double discountRate;

    public OrderDiscountRule(int thresholdTotal, double discountRate) {
        this.thresholdTotal = thresholdTotal;
        this.discountRate = discountRate;
    }

    @Override
    public double calculateDiscount(Cart cart) {
        int total = cart.total();
        return (total >= thresholdTotal) ? total * discountRate : 0.0;
    }
}
