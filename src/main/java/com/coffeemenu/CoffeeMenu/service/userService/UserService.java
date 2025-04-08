package com.coffeemenu.CoffeeMenu.service.userService;

import com.coffeemenu.CoffeeMenu.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
    User save(User user);
    User update(Long id, User user);
    void deleteById(Long id);
    Optional<User> findByUsername(String username);
}
