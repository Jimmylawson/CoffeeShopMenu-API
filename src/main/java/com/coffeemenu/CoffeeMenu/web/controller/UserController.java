package com.coffeemenu.CoffeeMenu.web.controller;

import com.coffeemenu.CoffeeMenu.web.model.login.LoginResponse;
import com.coffeemenu.CoffeeMenu.web.model.login.LoginRequest;
import com.coffeemenu.CoffeeMenu.web.model.userdto.UserRequestDto;
import com.coffeemenu.CoffeeMenu.web.mapper.UserMapper;
import com.coffeemenu.CoffeeMenu.entity.user.User;
import com.coffeemenu.CoffeeMenu.service.userService.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
   private final PasswordEncoder passwordEncoder;
   private final UserMapper userMapper;


   @Operation(
           summary = "Login user",
           description = "Authenticate a user with username and password"
   )
   @ApiResponse(
           responseCode = "200",
           description = "Login successful"

   )
   @PostMapping("/login")
   public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){

        /// Check from the db if  password is the same
        String token = userServiceImpl.login(loginRequest);
       return ResponseEntity.ok(new LoginResponse(token));
   }


   @Operation(
           summary = "Register user",
           description = "Register a new user"
   )
   @ApiResponse(
              responseCode = "200",
              description = "User registered successfully"
   )
    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDto user){
        ///Save the user
        User savedUser = userServiceImpl.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
    @Operation(
            summary = "Register admin user",
            description = "Register a new admin user"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Admin user registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Username already exists")
            }
    )

    @PostMapping("/user-admin")
    public ResponseEntity<?> registerAdminUser(@Valid @RequestBody UserRequestDto user){

        /// Set role to ADMIN
        var userRoles = new HashSet<>(user.getRoles());

        ///Save the user
        User savedUser = userServiceImpl.save(user);

        return ResponseEntity.ok("Admin registered successfully!");
    }
}
