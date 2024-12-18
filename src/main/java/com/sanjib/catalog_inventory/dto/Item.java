package com.sanjib.catalog_inventory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Item {

    private String name;       // Ensure this field is a String if 'name' is a text field
    private String category;   // Change category to String if it's a text-based field
    private Float price;       // Correct type for price
    private Integer stock;     // Correct type for stock

    // Constructor
    public Item(String name, String category, Float price, Integer stock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public Item() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Item{" +
               "name='" + name + '\'' +
               ", category='" + category + '\'' +
               ", price=" + price +
               ", stock=" + stock +
               '}';
    }
}
