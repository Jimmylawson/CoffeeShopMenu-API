package com.coffeemenu.CoffeeMenu.mapper;


import com.coffeemenu.CoffeeMenu.dto.userdto.UserRequestDto;
import com.coffeemenu.CoffeeMenu.dto.userdto.UserResponseDto;
import com.coffeemenu.CoffeeMenu.model.user.User;
import com.coffeemenu.CoffeeMenu.repository.UserRepository;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toResponse(User user);
    User toEntity(UserRequestDto request);


}
