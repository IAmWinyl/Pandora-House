package com.serraabak.pandorahouse;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

enum ItemType {
    TSHIRT,
    BLOUSE,
    PANTS,
    SHORT,
    SKIRT,
    DRESS,
    ACCESSORY,
    SHOES
}

@Entity 
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue
    private UUID itemId;

    private String itemName;
    private ItemType itemType;
    private Double itemPrice;

    public Item(String name, ItemType type, Double price) {
        itemName = name;
        itemType = type;
        itemPrice = price;
    }

    public UUID getItemID() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Double getItemPrice() {
        return itemPrice;
    }
}
