package com.coffeemenu.CoffeeMenu.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class LoginResponse {
    private final  String token;

}
