package com.coffeemenu.CoffeeMenu.controller;

import com.coffeemenu.CoffeeMenu.model.user.Role;
import com.coffeemenu.CoffeeMenu.model.user.User;
import com.coffeemenu.CoffeeMenu.service.userService.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
   private final PasswordEncoder passwordEncoder;

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user){

        /// Check if user already exists
        if(userServiceImpl.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Username already exist");
        }

        /// Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        ///Save the user
        User savedUser = userServiceImpl.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/user-admin")
    public ResponseEntity<?> registerAdminUser(@Valid @RequestBody User user){

        /// Check if user already exists
        if(userServiceImpl.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Username already exist");
        }

        /// Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        /// Set role to ADMIN
        user.setRoles(Set.of(Role.ADMIN));

        ///Save the user
        User savedUser = userServiceImpl.save(user);

        return ResponseEntity.ok("Admin registered successfully!");
    }
}
