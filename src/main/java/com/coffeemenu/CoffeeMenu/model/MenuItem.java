package com.coffeemenu.CoffeeMenu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name ="menu_items")
@RequiredArgsConstructor
@Getter @Setter
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    @Enumerated(EnumType.STRING)
    private Category category;

    private boolean available;
}
