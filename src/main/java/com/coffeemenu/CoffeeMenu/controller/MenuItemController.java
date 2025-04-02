package com.coffeemenu.CoffeeMenu.controller;

import com.coffeemenu.CoffeeMenu.dto.MenuItemRequest;
import com.coffeemenu.CoffeeMenu.dto.MenuItemResponse;
import com.coffeemenu.CoffeeMenu.exception.NotFoundException;

import com.coffeemenu.CoffeeMenu.mapper.MenuItemMapper;
import com.coffeemenu.CoffeeMenu.model.MenuItem;
import com.coffeemenu.CoffeeMenu.service.MenuItemServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menu-items")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemServiceImpl menuItemService;
    private final MenuItemMapper menuItemMapper;

    @GetMapping("")
    public ResponseEntity<List<MenuItemResponse>> getAllMenuItems(){
        var menuItems = menuItemService.findAll();

       var responseList = menuItems.stream()
               .map(menuItemMapper::toResponse)
               .toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getItemById(@PathVariable Long id){
        var menuItem = menuItemService.findById(id).orElseThrow(()->
                new NotFoundException("Menu item with ID " + id + " not found"));

        return ResponseEntity.ok(menuItemMapper.toResponse(menuItem));
    }

    @PostMapping("")
    public ResponseEntity<MenuItemResponse> createMenuItem(@Valid @RequestBody MenuItemRequest menuItemRequest){
        var menuItem = menuItemService.save(menuItemRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemMapper.toResponse(menuItem));
    }


    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(@PathVariable Long id,@Valid @RequestBody MenuItemRequest menuItemRequest){
        Optional<MenuItem> findExistingItem = menuItemService.findById(id);
        if(findExistingItem.isEmpty()) throw new NotFoundException("User with ID " + id + " not found");

        /// updating the item user id
        MenuItem menuItem = findExistingItem.get();
        /// Use mapper to copy fields from the request to the existing item
        menuItemMapper.updateMenuItemFromRequest(menuItemRequest, menuItem);

        /// saving it to the db
        var updateItem = menuItemService.update(menuItem.getId(),menuItemRequest);

        return ResponseEntity.status(HttpStatus.OK).body(
                menuItemMapper.toResponse(updateItem)
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        Optional<MenuItem> item = menuItemService.findById(id);
        if(item.isEmpty()) throw new NotFoundException("Menu item with ID " + id + " not found!");

        menuItemService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Item deleted successfully");
    }
}
