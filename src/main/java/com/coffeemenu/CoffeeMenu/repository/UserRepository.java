package com.coffeemenu.CoffeeMenu.repository;

import com.coffeemenu.CoffeeMenu.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
