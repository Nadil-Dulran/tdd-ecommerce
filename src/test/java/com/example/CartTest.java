package com.example;

import org.junit.jupiter.api.Test;

import com.example.Cart;
import com.example.Product;
import com.example.Catalog;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
   
 @Test
 void addRemoveAndTotal() {
    Catalog catalog = new Catalog();
    Product p = new Product("SKU1","Apple", 100);
    catalog.add(p);
    Cart cart = new Cart(catalog);
    cart.add("SKU1", 2);
    assertEquals(200, cart.total());
    cart.remove("SKU1");
    assertEquals(0, cart.total());
  }

  @Test
  void addingMoreThanAvailableFails() {
    Catalog catalog = new Catalog();
    Product p = new Product("SKU1","Apple", 100);
    catalog.add(p);
    InventoryService inventory = Mockito.mock(InventoryService.class);
    Mockito.when(inventory.getAvailable("SKU1")).thenReturn(1);
    Cart cart = new Cart(catalog, inventory);
    assertThrows(IllegalStateException.class, () -> cart.add("SKU1",2));
  }


}
