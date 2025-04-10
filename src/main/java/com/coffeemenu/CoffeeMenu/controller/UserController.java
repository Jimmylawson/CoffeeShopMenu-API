package com.coffeemenu.CoffeeMenu.controller;

import com.coffeemenu.CoffeeMenu.dto.userdto.UserRequestDto;
import com.coffeemenu.CoffeeMenu.dto.userdto.UserResponseDto;
import com.coffeemenu.CoffeeMenu.exception.UserNotFoundException;
import com.coffeemenu.CoffeeMenu.mapper.UserMapper;
import com.coffeemenu.CoffeeMenu.model.user.Role;
import com.coffeemenu.CoffeeMenu.model.user.User;
import com.coffeemenu.CoffeeMenu.service.userService.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

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
   public ResponseEntity<?> login(@Valid @RequestBody UserRequestDto user){
        /// the first line is the service call and the second should be the return call

        /// Check from the db if  password is the same

       return ResponseEntity.ok(" Login successful");
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
