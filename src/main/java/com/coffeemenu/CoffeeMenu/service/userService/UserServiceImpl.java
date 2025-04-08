package com.coffeemenu.CoffeeMenu.service.userService;

import com.coffeemenu.CoffeeMenu.exception.UserNotFoundException;
import com.coffeemenu.CoffeeMenu.model.User;
import com.coffeemenu.CoffeeMenu.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        var user = userRepository.findById(id);

        if(user.isEmpty()) throw new  UserNotFoundException("User with ID " + id + " not found");

        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
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
        return userRepository.findByUsername(username);
    }
}
