package com.coffeemenu.CoffeeMenu.mapper;


import com.coffeemenu.CoffeeMenu.dto.MenuItemRequest;
import com.coffeemenu.CoffeeMenu.dto.MenuItemResponse;
import com.coffeemenu.CoffeeMenu.model.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring") /// This will make it a spring bean
public interface MenuItemMapper {
    MenuItemResponse toResponse(MenuItem item);

    MenuItem toEntity(MenuItemRequest request);

    @Mapping(target = "id", ignore = true) /// This will ignore the id field
    void updateMenuItemFromRequest(MenuItemRequest request,@MappingTarget MenuItem entity);


}
