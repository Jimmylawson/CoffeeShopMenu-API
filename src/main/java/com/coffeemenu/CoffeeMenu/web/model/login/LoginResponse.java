package com.coffeemenu.CoffeeMenu.web.model.login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class LoginResponse {
    private final  String token;

}
