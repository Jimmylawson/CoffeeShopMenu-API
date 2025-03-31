package com.coffeemenu.CoffeeMenu.dto;

import com.coffeemenu.CoffeeMenu.model.Category;

public record MenuItemRequest(String name,String description, double price, Category category, boolean available){

    // Extra constructor with default available = true
    public MenuItemRequest(String name, String description, double price, Category category) {
        this(name, description, price, category, false);
    }
}
