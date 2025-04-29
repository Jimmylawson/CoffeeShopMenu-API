
package com.coffeemenu.CoffeeMenu.web.model.menudto;

import com.coffeemenu.CoffeeMenu.entity.menuItem.Category;

public record MenuItemResponse(String name, String description, Double price, Category category, Boolean available){

    // Extra constructor with default available = true
    public MenuItemResponse(String name, String description, double price, Category category) {
        this(name, description, price, category, false);
    }
}
