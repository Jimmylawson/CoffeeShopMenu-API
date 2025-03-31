package com.coffeemenu.CoffeeMenu.service;


import com.coffeemenu.CoffeeMenu.dto.MenuItemRequest;

import com.coffeemenu.CoffeeMenu.exception.UserNotFoundException;
import com.coffeemenu.CoffeeMenu.model.MenuItem;
import com.coffeemenu.CoffeeMenu.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItem save(MenuItemRequest menuItemRequest) {
        MenuItem menuItem = new MenuItem();

        menuItem.setName(menuItemRequest.name());
        menuItem.setDescription(menuItemRequest.description());
        menuItem.setPrice(menuItemRequest.price());
        menuItem.setCategory(menuItemRequest.category());
        menuItem.setAvailable(menuItemRequest.available());

        return menuItemRepository.save(menuItem);
    }

    public MenuItem update(MenuItem menuItem){
        return menuItemRepository.save(menuItem);
    }
    public Optional<MenuItem> findById(Long id){
        return menuItemRepository.findById(id);
    }


    public List<MenuItem> findAll(){
        return menuItemRepository.findAll();

    }


    public void deleteById(Long id){
        if(!menuItemRepository.existsById(id)){
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        menuItemRepository.deleteById(id);

    }



}