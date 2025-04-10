package com.coffeemenu.CoffeeMenu.service.userService;

import com.coffeemenu.CoffeeMenu.dto.userdto.UserRequestDto;
import com.coffeemenu.CoffeeMenu.dto.userdto.UserResponseDto;
import com.coffeemenu.CoffeeMenu.exception.UserAlreadyExistsException;
import com.coffeemenu.CoffeeMenu.exception.UserNotFoundException;
import com.coffeemenu.CoffeeMenu.mapper.UserMapper;
import com.coffeemenu.CoffeeMenu.model.user.Role;
import com.coffeemenu.CoffeeMenu.model.user.User;
import com.coffeemenu.CoffeeMenu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findById(Long id) {
        var user = userRepository.findById(id);

        if(user.isEmpty()) throw new  UserNotFoundException("User with ID " + id + " not found");

        return user;
    }

    @Override
    public User save(UserRequestDto user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }
        /// Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));



        return userRepository.save(userMapper.toEntity(user));
    }

    @Override
    public User update(Long id, User user) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User with ID " + id + " not found"));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setRoles(user.getRoles());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User with ID " + id + " not found"));

        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.of(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"))
        );
    }

    /// Creating a method to check if passwords matches
    public UserResponseDto authenticateUser(String username,String rawPassword){
        var user = findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));

        if(!passwordEncoder.matches(rawPassword,user.getPassword())){
            throw  new BadCredentialsException("Invalid password");
        }

        return userMapper.toResponse(user);
    }



}
