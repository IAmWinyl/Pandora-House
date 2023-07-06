package com.serraabak.pandorahouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 

@ExtendWith(MockitoExtension.class)
public class ItemRestControllerUnitTests {
    
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemController itemController;

    @BeforeEach
    void setup() {
        itemRepository = mock(ItemRepository.class);
        itemController = new ItemController(itemRepository);
    }

    // Tests for listAllItems method
    @Test
    void givenRepository_whenFindAll_returnItemList_200() throws Exception {
        // Given
        List<Item> items = new ArrayList<Item>() {{
            add(new Item("example1",ItemType.TSHIRT,100.00,1,"example1.jpg"));
            add(new Item("example2",ItemType.PANTS,200.00,2,"example2.jpg"));
            add(new Item("example2",ItemType.SHOES,40.00,3,"example3.jpg"));
        }};
        ResponseEntity<List<Item>> expected = new ResponseEntity<List<Item>>(items, HttpStatus.OK);

        // When
        when(itemRepository.findAll()).thenReturn(items);
        ResponseEntity<?> response = itemController.listAllItems();
        
        // Then
        verify(itemRepository).findAll();
        assertEquals(expected, response);
    }

    // Tests for addNewItem method
    @Test
    void givenItem_whenSave_returnItem_201() throws Exception {
        // Given
        Item item = new Item("Test Name",ItemType.TSHIRT,100.00,1,"example.jpg");
        ResponseEntity<Item> expected = new ResponseEntity<Item>(item, HttpStatus.CREATED);

        // When
        when(itemRepository.save(item)).thenReturn(item);
        ResponseEntity<?> response = itemController.addNewItem(item);
        
        // Then
        verify(itemRepository).save(item);
        assertEquals(expected, response);
    }

    // Tests for updateItem method
    @Test
    void givenExistingItemAndNewField_whenSave_returnNewItem_200() throws Exception {
        // Given
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        Item oldItem = new Item("Test Name",ItemType.TSHIRT,100.00,1,"example.jpg");
        Item newItem = new Item("New Test Name",ItemType.TSHIRT,100.00,1,"example.jpg");

        List<Item> items = new ArrayList<>();
        items.add(oldItem);

        long exampleId = oldItem.getId();
        newItem.setId(exampleId);
        assertEquals(oldItem.getId(), newItem.getId());

        Map<String,String> jsonRequest = new HashMap<>();
        jsonRequest.put("name","New Test Name");

        ResponseEntity<Item> expected = new ResponseEntity<Item>(newItem, HttpStatus.OK);
        String jsonExpected = ow.writeValueAsString(expected.getBody());

        // When
        when(itemRepository.findById(exampleId)).thenReturn(items);
        when(itemRepository.save(any(Item.class))).thenReturn(newItem);

        ResponseEntity<?> response = itemController.updateItem(exampleId, jsonRequest);
        String jsonResponse = ow.writeValueAsString(response.getBody());

        // Then
        verify(itemRepository).findById(exampleId);
        verify(itemRepository).save(any(Item.class));
        assertEquals(expected.getStatusCode(), response.getStatusCode());
        assertEquals(jsonExpected, jsonResponse);
    }

    // Tests for deleteItem method
    @Test
    void givenExistingItem_whenDelete_returnSuccess_200() throws Exception {
        // Given
        Item item = new Item("Test Name",ItemType.TSHIRT,100.00,1,"example.jpg");
        long exampleId = 1;

        List<Item> items = new ArrayList<>();
        items.add(item);

        ResponseEntity<String> expected = new ResponseEntity<>("Success.", HttpStatus.OK);

        // When
        when(itemRepository.findById(exampleId)).thenReturn(items);

        ResponseEntity<?> response = itemController.deleteItem(exampleId);
        
        // Then
        verify(itemRepository).findById(exampleId);
        verify(itemRepository).delete(any(Item.class));
        assertEquals(expected, response);
    }
    
}