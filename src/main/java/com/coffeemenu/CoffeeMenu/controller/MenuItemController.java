package com.coffeemenu.CoffeeMenu.controller;

import com.coffeemenu.CoffeeMenu.dto.MenuItemRequest;
import com.coffeemenu.CoffeeMenu.dto.MenuItemResponse;
import com.coffeemenu.CoffeeMenu.exception.NotFoundException;

import com.coffeemenu.CoffeeMenu.mapper.MenuItemMapper;
import com.coffeemenu.CoffeeMenu.model.MenuItem;
import com.coffeemenu.CoffeeMenu.service.MenuItemServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.hibernate.sql.Update;
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


    @Operation(
            summary = "Get all menu items",
            description = "Retrieve a list of all menu items"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Items found"),
            @ApiResponse(responseCode = "404", description = "No items found")
    })
    @GetMapping("")
    public ResponseEntity<List<MenuItemResponse>> getAllMenuItems(){
        var menuItems = menuItemService.findAll();

       var responseList = menuItems.stream()
               .map(menuItemMapper::toResponse)
               .toList();

        return ResponseEntity.ok(responseList);
    }
    @Operation(
            summary = "Get menu item by ID",
            description = "Retrieve a single item by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found"),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getItemById(@PathVariable Long id){
        var menuItem = menuItemService.findById(id).orElseThrow(()->
                new NotFoundException("Menu item with ID " + id + " not found"));

        return ResponseEntity.ok(menuItemMapper.toResponse(menuItem));
    }


    @Operation(
            summary = "Create a new menu item",
            description = "Add a new item to the menu"
    )
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Item created"),
            @ApiResponse(responseCode ="400",description="Invalid input")
    })
    @PostMapping("")
    public ResponseEntity<MenuItemResponse> createMenuItem(@Valid @RequestBody MenuItemRequest menuItemRequest){
        var menuItem = menuItemService.save(menuItemRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemMapper.toResponse(menuItem));
    }



    @Operation(
            summary ="Update am existing menu item",
            description = "Update the details of an existing item"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item updated"),
        @ApiResponse(responseCode = "404", description = "Item not found"),
    })


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

    /// Patch endpoint to update a  field
    @PatchMapping("/{id}")
    public ResponseEntity<MenuItemResponse> patchMenuItem(@PathVariable Long id,@Valid @RequestBody MenuItemRequest menuItemRequest){
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
    @Operation(
            summary = "Delete a menu item",
            description = "Remove an item from the menu"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item deleted"),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id){
        Optional<MenuItem> item = menuItemService.findById(id);
        if(item.isEmpty()) throw new NotFoundException("Menu item with ID " + id + " not found!");

        menuItemService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Item deleted successfully");
    }
}
