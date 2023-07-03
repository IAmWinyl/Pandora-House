package com.serraabak.pandorahouse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findById(long id);

    List<Item> findByName(String name);

    List<Item> findByType(ItemType type);
    
    List<Item> findByPrice(double price);

    List<Item> findByQuantity(int quantity);
}