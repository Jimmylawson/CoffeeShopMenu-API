package com.coffeemenu.CoffeeMenu.repository;

import com.coffeemenu.CoffeeMenu.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

}
