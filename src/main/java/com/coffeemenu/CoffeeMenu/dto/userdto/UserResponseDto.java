package com.coffeemenu.CoffeeMenu.dto.userdto;


import com.coffeemenu.CoffeeMenu.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private String username;
    private Set<Role> roles;
}
