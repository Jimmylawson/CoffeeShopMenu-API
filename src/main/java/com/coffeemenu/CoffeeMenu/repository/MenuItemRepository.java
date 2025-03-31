package com.coffeemenu.CoffeeMenu.repository;

import com.coffeemenu.CoffeeMenu.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

}
