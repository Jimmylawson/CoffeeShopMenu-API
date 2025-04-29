package com.coffeemenu.CoffeeMenu.service.menuService;

import com.coffeemenu.CoffeeMenu.web.model.menudto.MenuItemRequest;
import com.coffeemenu.CoffeeMenu.entity.menuItem.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemService {
    MenuItem save(MenuItemRequest menuItemRequest);
    MenuItem update(Long id, MenuItemRequest menuItem);
    Optional<MenuItem> findById(Long id);
    List<MenuItem> findAll();
    void deleteById(Long id);
}

