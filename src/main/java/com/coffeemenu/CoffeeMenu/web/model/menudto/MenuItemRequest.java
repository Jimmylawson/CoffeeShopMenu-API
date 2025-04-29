package com.coffeemenu.CoffeeMenu.web.model.menudto;

import com.coffeemenu.CoffeeMenu.entity.menuItem.Category;

public record MenuItemRequest(String name,String description, Double price, Category category, Boolean available){


}
