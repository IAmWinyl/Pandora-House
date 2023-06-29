package com.serraabak.pandorahouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<List<Item>> getAllItems() {
        try {
            List<Item> items = new ArrayList<Item>();
            itemRepository.findAll().forEach(items::add);

            if (items.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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

    @PostMapping("/new")
    public ResponseEntity<Item> createNewItem(@RequestBody Item newItem) {
        try {
            // TODO: validate newItem 
            Item item = itemRepository.save(newItem);
            return new ResponseEntity<Item>(item, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<Long> buyItem(@RequestBody Map<String, String> jsonRequest) {
        try {
            long itemId = Long.parseLong(jsonRequest.get("id"));
            int buyQuantity = Integer.parseInt(jsonRequest.get("quantity"));

            Optional<Item> item_optional = itemRepository.findById(itemId);

            if (item_optional.isPresent()) {
                Item item = item_optional.get();
                int itemQuantity = item.getQuantity();

                if (!Utils.willSubtractionOverflow(itemQuantity,buyQuantity) && (itemQuantity - buyQuantity >= 0)) {
                    item.setQuantity(item.getQuantity()-buyQuantity);
                    itemRepository.save(item);
                    return new ResponseEntity<Long>(itemId, HttpStatus.CREATED); 
                }
                else {
                    //System.out.println("Error. Item with ID " + String.valueOf(itemId) + " doesn't have enough units.");
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
            }
            else {
                //System.out.println("Error. Item with ID " + String.valueOf(itemId) + " could not be found.");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
