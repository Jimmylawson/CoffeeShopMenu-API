package com.coffeemenu.CoffeeMenu.repository;

import com.coffeemenu.CoffeeMenu.entity.menuItem.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

}
