package com.coffeemenu.CoffeeMenu.controller;

import com.coffeemenu.CoffeeMenu.dto.MenuItemRequest;
import com.coffeemenu.CoffeeMenu.dto.MenuItemResponse;
import com.coffeemenu.CoffeeMenu.exception.NotFoundException;

import com.coffeemenu.CoffeeMenu.model.MenuItem;
import com.coffeemenu.CoffeeMenu.service.MenuItemService;
import com.coffeemenu.CoffeeMenu.utils.MenuItemMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menu-items")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    @GetMapping("")
    public ResponseEntity<List<MenuItemResponse>> getAllMenuItems(){
        var menuItems = menuItemService.findAll();

        if(menuItems.isEmpty()){
            throw new NotFoundException("Menu Items not found");
        }

       var responseList = menuItems.stream()
               .map(MenuItemMapper::toResponse)
               .toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getItemById(@PathVariable Long id){
        var menuItem = menuItemService.findById(id);

        return menuItem
                .map(MenuItemMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<MenuItemResponse> createMenuItem(@Valid @RequestBody MenuItemRequest menuItemRequest){
        var menuItem = menuItemService.save(menuItemRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(MenuItemMapper.toResponse(menuItem));
    }


    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(@PathVariable Long id,@Valid @RequestBody MenuItemRequest menuItemRequest){
        Optional<MenuItem> findItem = menuItemService.findById(id);
        if(findItem.isEmpty()) throw new NotFoundException("User with ID " + id + " not found");

        /// updating the item user id
        MenuItem menuItem = findItem.get();
        /// Update fields
        menuItem.setName(menuItemRequest.name());
        menuItem.setDescription(menuItemRequest.description());
        menuItem.setPrice(menuItemRequest.price());
        menuItem.setCategory(menuItemRequest.category());
        menuItem.setAvailable(menuItemRequest.available());

        /// saving it to the db
        var response = menuItemService.update(menuItem);

        return ResponseEntity.status(HttpStatus.OK).body(MenuItemMapper.toResponse(response));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        Optional<MenuItem> item = menuItemService.findById(id);
        if(item.isEmpty()) throw new NotFoundException("Menu item with ID " + id + " not found!");
        menuItemService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Item deleted successfully");
    }
}
