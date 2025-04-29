package com.coffeemenu.CoffeeMenu.web.mapper;


import com.coffeemenu.CoffeeMenu.web.model.userdto.UserRequestDto;
import com.coffeemenu.CoffeeMenu.web.model.userdto.UserResponseDto;
import com.coffeemenu.CoffeeMenu.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toResponse(User user);
    User toEntity(UserRequestDto request);


}
