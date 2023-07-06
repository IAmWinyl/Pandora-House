package com.serraabak.pandorahouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private final ItemRepository itemRepository;

    private Item findItem(long id) throws Exception {
        List<Item> itemsFound = itemRepository.findById(id);

        // Error-checking
        if (itemsFound.size() < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No item found with that id.");
        }

        return itemsFound.get(0);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Item>> listAllItems() {
        try {
            List<Item> items = new ArrayList<Item>();
            itemRepository.findAll().forEach(items::add);

            if (items.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Item> fetchItem(@PathVariable("id") long id) {
        try {
            Item item = findItem(id);

            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping(
        path = "/add",
        consumes = "application/json",
        produces = "application/json")
    public ResponseEntity<Item> addNewItem(@RequestBody Item newItem) {
        try {
            // TODO: validate newItem 
            Item item = itemRepository.save(newItem);
            return new ResponseEntity<Item>(item, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping(
        path = "/delete/{id}",
        produces = "application/json")
    public ResponseEntity<String> deleteItem(@PathVariable("id") long id) {
        try {
            Item item = findItem(id);

            itemRepository.delete(item);

            return new ResponseEntity<String>("Success.", HttpStatus.OK);
        } catch (ResponseStatusException rse) {
            throw rse;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping(
        path = "/update/{id}",
        produces = "application/json")
    public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Map<String, String> jsonRequest) {
        try {
            Item item = findItem(id);

            // TODO: Validate these
            if (jsonRequest.containsKey("name")) {
                item.setName(jsonRequest.get("name"));
            }
            if (jsonRequest.containsKey("type")) {
                item.setType(jsonRequest.get("type"));
            }
            if (jsonRequest.containsKey("price")) {
                item.setPrice(Double.parseDouble(jsonRequest.get("price")));
            }
            if (jsonRequest.containsKey("quantity")) {
                item.setQuantity(Integer.parseInt(jsonRequest.get("quantity")));
            }
            if (jsonRequest.containsKey("imgUrl")) {
                item.setImgUrl(jsonRequest.get("imgUrl"));
            }

            itemRepository.save(item);

            return new ResponseEntity<Item>(item, HttpStatus.OK);
        } catch (ResponseStatusException rse) {
            throw rse;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @PostMapping(
        path = "/buy/{id}",
        consumes = "application/json",
        produces = "application/json")
    public ResponseEntity<Long> buyItem(@PathVariable("id") long id, @RequestBody int buyQuantity) {
        try {
            Item item = findItem(id);
            int itemQuantity = item.getQuantity();

            if (!Utils.willSubtractionOverflow(itemQuantity,buyQuantity) && (itemQuantity - buyQuantity >= 0)) {
                item.setQuantity(item.getQuantity()-buyQuantity);
                itemRepository.save(item);
                return new ResponseEntity<Long>(id, HttpStatus.OK); 
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Inventory quantity is insufficient for the request.");
            }
            
        } catch (ResponseStatusException rse) {
            throw rse;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
