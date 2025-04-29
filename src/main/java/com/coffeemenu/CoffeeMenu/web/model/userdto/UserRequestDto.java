package com.coffeemenu.CoffeeMenu.web.model.userdto;

import com.coffeemenu.CoffeeMenu.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter @Setter
public class UserRequestDto {

    private String username;
    private String password;
    private Set<Role> roles;

}
