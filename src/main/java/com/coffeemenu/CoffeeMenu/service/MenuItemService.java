package com.coffeemenu.CoffeeMenu.service;

import com.coffeemenu.CoffeeMenu.dto.MenuItemRequest;
import com.coffeemenu.CoffeeMenu.dto.MenuItemResponse;
import com.coffeemenu.CoffeeMenu.model.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemService {
    MenuItem save(MenuItemRequest menuItemRequest);
    MenuItem update(Long id, MenuItemRequest menuItem);
    Optional<MenuItem> findById(Long id);
    List<MenuItem> findAll();
    void deleteById(Long id);
}

