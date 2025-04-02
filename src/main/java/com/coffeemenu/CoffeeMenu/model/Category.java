package com.coffeemenu.CoffeeMenu.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Category {
    COFFEE,TEA,LATTE,SMOOTHIE,CAKE,ESPRESSO,MACHO;


    @JsonCreator
    public static Category fromString(String name) {
        return Category.valueOf(name.toUpperCase());
    }

}
