package com.coffeemenu.CoffeeMenu.web.mapper;


import com.coffeemenu.CoffeeMenu.web.model.menudto.MenuItemRequest;
import com.coffeemenu.CoffeeMenu.web.model.menudto.MenuItemResponse;
import com.coffeemenu.CoffeeMenu.entity.menuItem.MenuItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring") /// This will make it a spring bean
public interface MenuItemMapper {
    MenuItemResponse toResponse(MenuItem item);

    MenuItem toEntity(MenuItemRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMenuItemFromRequest(MenuItemRequest request,@MappingTarget MenuItem entity);


}
