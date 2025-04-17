package com.coffeemenu.CoffeeMenu.dto.menudto;

import com.coffeemenu.CoffeeMenu.model.menuItem.Category;

public record MenuItemRequest(String name,String description, Double price, Category category, Boolean available){


}
