package com.coffeemenu.CoffeeMenu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_item")
@Setter
@Getter
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotBlank
    private String name;

//    @NotBlank
    private String description;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Category category;

    private Boolean available;
}
