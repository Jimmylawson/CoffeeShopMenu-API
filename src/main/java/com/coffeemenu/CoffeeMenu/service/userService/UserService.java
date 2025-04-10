package com.coffeemenu.CoffeeMenu.service.userService;

import com.coffeemenu.CoffeeMenu.dto.login.LoginRequest;
import com.coffeemenu.CoffeeMenu.dto.userdto.UserRequestDto;
import com.coffeemenu.CoffeeMenu.dto.userdto.UserResponseDto;
import com.coffeemenu.CoffeeMenu.model.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    User save(UserRequestDto user);
    User update(Long id, User user);
    void deleteById(Long id);
    Optional<User> findByUsername(String username);
    UserResponseDto authenticateUser(String username,String rawPassword);
    String login(LoginRequest loginRequest);
}
