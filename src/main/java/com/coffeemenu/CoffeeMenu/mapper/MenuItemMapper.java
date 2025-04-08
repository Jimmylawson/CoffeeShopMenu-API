package com.coffeemenu.CoffeeMenu.mapper;


import com.coffeemenu.CoffeeMenu.dto.MenuItemRequest;
import com.coffeemenu.CoffeeMenu.dto.MenuItemResponse;
import com.coffeemenu.CoffeeMenu.model.MenuItem;
import com.coffeemenu.CoffeeMenu.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring") /// This will make it a spring bean
public interface MenuItemMapper {
    MenuItemResponse toResponse(MenuItem item);

    MenuItem toEntity(MenuItemRequest request);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMenuItemFromRequest(MenuItemRequest request,@MappingTarget MenuItem entity);


}
