package com.coffeemenu.CoffeeMenu.service.userService;

import com.coffeemenu.CoffeeMenu.web.model.login.LoginRequest;
import com.coffeemenu.CoffeeMenu.web.model.userdto.UserRequestDto;
import com.coffeemenu.CoffeeMenu.web.model.userdto.UserResponseDto;
import com.coffeemenu.CoffeeMenu.web.exception.UserAlreadyExistsException;
import com.coffeemenu.CoffeeMenu.web.exception.UserNotFoundException;
import com.coffeemenu.CoffeeMenu.web.mapper.UserMapper;
import com.coffeemenu.CoffeeMenu.entity.user.User;
import com.coffeemenu.CoffeeMenu.repository.UserRepository;
import com.coffeemenu.CoffeeMenu.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public Optional<User> findById(Long id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) throw new UserNotFoundException("User with ID " + id + " not found");

        return user;
    }

    @Override
    public User save(UserRequestDto user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }
        /// Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(userMapper.toEntity(user));
    }

    @Override
    public User update(Long id, User user) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setRoles(user.getRoles());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.of(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException("Bad Credentials"))
        );
    }

    /// Creating a method to check if passwords matches
    @Override
    public UserResponseDto authenticateUser(String username, String rawPassword) {
        var user = findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Bad Credentials"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return userMapper.toResponse(user);
    }


    /// This is wrong. Use AuthenticationManager to do this part of it.

    @Override
    public String login(LoginRequest loginRequest) {
        /// Using authentication to authentication the username and generate a token
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        /// Going to hold the authentication of the user so the user don't need to login everytime or the lifetime of the login
        SecurityContextHolder.getContext().setAuthentication(authentication);


        /// Because the authenticationManager returns an object, we can get the authenticated user from the Authentication
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        /// Generate JWT token
        return jwtTokenUtil.generateToken(userDetails);

    }


}
