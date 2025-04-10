package com.coffeemenu.CoffeeMenu.dto.login;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class LoginRequest {
    private String username;
    private String password;
}
