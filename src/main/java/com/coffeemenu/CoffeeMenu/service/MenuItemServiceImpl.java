package com.coffeemenu.CoffeeMenu.service;


import com.coffeemenu.CoffeeMenu.dto.MenuItemRequest;

import com.coffeemenu.CoffeeMenu.exception.UserNotFoundException;
import com.coffeemenu.CoffeeMenu.mapper.MenuItemMapper;
import com.coffeemenu.CoffeeMenu.model.MenuItem;
import com.coffeemenu.CoffeeMenu.repository.MenuItemRepository;

import lombok.RequiredArgsConstructor;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;


    @Override
    public MenuItem save(MenuItemRequest menuItemRequest) {
        return menuItemRepository.save(menuItemMapper.toEntity(menuItemRequest));
    }

    @Override
    public MenuItem update(Long id, MenuItemRequest menuItemRequest) {
        var existingItem = menuItemRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User item with ID " + id + " not found"));

        menuItemMapper.updateMenuItemFromRequest(menuItemRequest,existingItem);

        return menuItemRepository.save(existingItem);

    }

    @Override
    public Optional<MenuItem> findById(Long id) {
        var findItem = menuItemRepository.findById(id);

        if (findItem.isEmpty()) {
            throw new UserNotFoundException("User item with ID " + id + " not found");
        }
        return findItem;

    }

    @Override
    public List<MenuItem> findAll() {
        return menuItemRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User item with ID " + id + " not found"));

        menuItemRepository.deleteById(item.getId());


    }
}