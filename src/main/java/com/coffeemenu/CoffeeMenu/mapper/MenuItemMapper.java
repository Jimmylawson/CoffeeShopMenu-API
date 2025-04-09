package com.coffeemenu.CoffeeMenu.mapper;


import com.coffeemenu.CoffeeMenu.dto.menudto.MenuItemRequest;
import com.coffeemenu.CoffeeMenu.dto.menudto.MenuItemResponse;
import com.coffeemenu.CoffeeMenu.model.menuItem.MenuItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring") /// This will make it a spring bean
public interface MenuItemMapper {
    MenuItemResponse toResponse(MenuItem item);

    MenuItem toEntity(MenuItemRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMenuItemFromRequest(MenuItemRequest request,@MappingTarget MenuItem entity);


}
