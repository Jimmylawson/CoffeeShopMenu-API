
package com.coffeemenu.CoffeeMenu.dto.menudto;

import com.coffeemenu.CoffeeMenu.model.menuItem.Category;

public record MenuItemResponse(String name, String description, Double price, Category category, Boolean available){

    // Extra constructor with default available = true
    public MenuItemResponse(String name, String description, double price, Category category) {
        this(name, description, price, category, false);
    }
}
