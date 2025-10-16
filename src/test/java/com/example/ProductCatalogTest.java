package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.Cart;
import com.example.Product;
import com.example.Catalog;

class ProductCatalogTest {

  @Test
  void createProduct_requiresSkuNamePrice() {
    Product p = new Product("SKU1","Apple", 100);
    assertEquals("SKU1", p.getSku());
    assertEquals("Apple", p.getName());
    assertEquals(100, p.getPrice());
  }

  @Test
  void catalogReturnsProductBySku_orNullWhenMissing() {
    Catalog catalog = new Catalog();
    Product p = new Product("SKU1","Apple", 100);
    catalog.add(p);
    assertEquals(p, catalog.findBySku("SKU1"));
    assertNull(catalog.findBySku("MISSING"));
  }
}
