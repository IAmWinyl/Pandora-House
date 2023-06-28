package com.serraabak.pandorahouse;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private UUID id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private ItemType type;

    @Column(name = "price")
    private Double price;

    public Item() {
        
    }

    public Item(String name, ItemType type, Double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
