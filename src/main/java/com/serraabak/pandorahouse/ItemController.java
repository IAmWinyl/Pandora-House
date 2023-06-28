package com.serraabak.pandorahouse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins ="http://localhost:9000")
@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItems() {
        try {
            List<Item> items = new ArrayList<Item>();
            itemRepository.findAll().forEach(items::add);

            if (items.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @GetMapping("/prices")
    public ResponseEntity<List<Item>> getItemsWithPrice() {
        try {
            List<Item> items = new ArrayList<Item>();

            itemRepository.findByPrice(1.5).forEach(items::add);

            if (items.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/newitem")
    public ResponseEntity<Item> createNewItem(@RequestBody Item newItem) {
        try {
            Item item = itemRepository.save(new Item(newItem.getName(), newItem.getType(), newItem.getPrice()));
            return new ResponseEntity<Item>(item, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
