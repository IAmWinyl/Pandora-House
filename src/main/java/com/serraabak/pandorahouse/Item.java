package com.serraabak.pandorahouse;

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
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private ItemType type;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private int quantity;

    public Item() {
        
    }

    public Item(String name, ItemType type, Double price, int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setId(long id) {
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
