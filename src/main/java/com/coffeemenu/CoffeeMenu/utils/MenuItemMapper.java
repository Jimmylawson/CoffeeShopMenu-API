package com.coffeemenu.CoffeeMenu.utils;

import com.coffeemenu.CoffeeMenu.dto.MenuItemResponse;
import com.coffeemenu.CoffeeMenu.model.MenuItem;

public class MenuItemMapper {
    public static MenuItemResponse toResponse(MenuItem item){
        return new MenuItemResponse(
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getCategory(),
                item.isAvailable()
        );

    }
}
