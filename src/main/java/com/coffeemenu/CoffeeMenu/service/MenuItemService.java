package com.coffeemenu.CoffeeMenu.service;


import com.coffeemenu.CoffeeMenu.dto.MenuItemRequest;
import com.coffeemenu.CoffeeMenu.dto.MenuItemResponse;
import com.coffeemenu.CoffeeMenu.exception.UserNotFoundException;
import com.coffeemenu.CoffeeMenu.model.MenuItem;
import com.coffeemenu.CoffeeMenu.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItemResponse save(MenuItemRequest menuItemRequest){
        var menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.name());
        menuItem.setDescription(menuItemRequest.description());
        menuItem.setPrice(menuItemRequest.price());
        menuItem.setCategory(menuItemRequest.category());
        menuItem.setAvailable(menuItemRequest.available());
        menuItemRepository.save(menuItem);
        return new MenuItemResponse(menuItem.getName(),menuItem.getDescription(),menuItem.getPrice(),
                menuItem.getCategory(),menuItem.isAvailable());

    }
    public Optional<MenuItemResponse> findById(Long id){
        var menuItem = menuItemRepository.findById(id);

        return menuItem.map(item ->new MenuItemResponse(item.getName(),
                item.getDescription(),item.getPrice(),item.getCategory(),item.isAvailable()));
    }

    public void deleteById(Long id){
        if(!menuItemRepository.existsById(id)){
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        menuItemRepository.deleteById(id);

    }



}