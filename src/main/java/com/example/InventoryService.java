package com.example;

/**
 * Provides available stock for each SKU.
 */
@FunctionalInterface
public interface InventoryService {
    int getAvailable(String sku);

}