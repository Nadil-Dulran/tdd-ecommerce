package com.example;

public class Product {
  private final String sku;
  private final String name;
  private final int price;
  public Product(String sku, String name, int price){
    if(sku==null||name==null||price<0) throw new IllegalArgumentException();
    this.sku=sku; this.name=name; this.price=price;
  }
  public String getSku(){return sku;}
  public String getName(){return name;}
  public int getPrice(){return price;}
}
