package com.example;
import java.util.*;
public class Catalog {
  private final Map<String,Product> store = new HashMap<>();
  public void add(Product p){ store.put(p.getSku(), p); }
  public Product findBySku(String sku){ return store.get(sku); }
}
