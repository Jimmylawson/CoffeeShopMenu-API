package com.coffeemenu.CoffeeMenu.securityConfig;

import com.coffeemenu.CoffeeMenu.exception.UserNotFoundException;
import com.coffeemenu.CoffeeMenu.repository.UserRepository;
import jakarta.servlet.Servlet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeMenuDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final Servlet servlet;

    /**
     * This method is used to load user by username
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));


        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRoles()));

        return new User(user.getUsername(),user.getPassword(),authorities);
    }
}
