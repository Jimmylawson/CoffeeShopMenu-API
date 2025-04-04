package com.coffeemenu.CoffeeMenu.dto;

import com.coffeemenu.CoffeeMenu.model.Category;

public record MenuItemRequest(String name,String description, Double price, Category category, Boolean available){


}
