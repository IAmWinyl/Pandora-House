package com.serraabak.pandorahouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter;

import io.micrometer.core.ipc.http.HttpSender.Response; 

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
    void givenFullRepository_whenFindAll_returnItemList_200() throws Exception {
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

    @Test
    void givenEmptyRepository_whenFindAll_returnNull_204() throws Exception {
        // Given
        List<Item> list = new ArrayList<>();
        ResponseEntity<List<Item>> expected = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        // When
        when(itemRepository.findAll()).thenReturn(list);
        ResponseEntity<?> response = itemController.listAllItems();
        
        // Then
        verify(itemRepository).findAll();
        assertEquals(expected, response);
    }

    // Tests for fetchItem method
    @Test
    void givenExistingId_whenFind_returnItem_200() throws Exception {
        // Given
        Item item = new Item("Test Name",ItemType.TSHIRT,100.00,1,"example.jpg");
        List<Item> items = new ArrayList<>();
        items.add(item);
        long exampleId = item.getId();
        ResponseEntity<Item> expected = new ResponseEntity<>(items.get(0), HttpStatus.OK);

        // When
        when(itemRepository.findById(exampleId)).thenReturn(items);
        ResponseEntity<?> response = itemController.fetchItem(exampleId);

        // Then
        verify(itemRepository).findById(exampleId);
        assertEquals(expected, response);
    } 

    @Test
    void givenNonexistentId_whenFind_throwsException_404() throws Exception {
         // Given

        // When
        when(itemRepository.findById(0))
            .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND,"No item found with that id."));

        // Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,() -> itemController.fetchItem(0));
        
        assertEquals("404 NOT_FOUND \"No item found with that id.\"",exception.getMessage());
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

    @Test
    void givenExistingItemAndMultipleNewFields_whenSave_returnNewItem_200() throws Exception {
        // Given
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        Item oldItem = new Item("Test Name",ItemType.TSHIRT,100.00,1,"example.jpg");
        Item newItem = new Item("New Test Name",ItemType.TSHIRT,300.50,1,"example.jpg");

        List<Item> items = new ArrayList<>();
        items.add(oldItem);

        long exampleId = oldItem.getId();
        newItem.setId(exampleId);
        assertEquals(oldItem.getId(), newItem.getId());

        Map<String,String> jsonRequest = new HashMap<>();
        jsonRequest.put("name","New Test Name");
        jsonRequest.put("price","300.50");

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

    @Test
    void givenExistingItemAndBlankFields_whenSave_returnItem_200() throws Exception {
        // Given
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        Item item = new Item("Test Name",ItemType.TSHIRT,100.00,1,"example.jpg");
        long exampleId = item.getId();

        List<Item> items = new ArrayList<>();
        items.add(item);

        Map<String,String> jsonRequest = new HashMap<>();

        ResponseEntity<Item> expected = new ResponseEntity<Item>(item, HttpStatus.OK);
        String jsonExpected = ow.writeValueAsString(expected.getBody());

        // When
        when(itemRepository.findById(exampleId)).thenReturn(items);
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        ResponseEntity<?> response = itemController.updateItem(exampleId, jsonRequest);
        String jsonResponse = ow.writeValueAsString(response.getBody());

        // Then
        verify(itemRepository).findById(exampleId);
        verify(itemRepository).save(any(Item.class));
        assertEquals(expected.getStatusCode(), response.getStatusCode());
        assertEquals(jsonExpected, jsonResponse);
    }

    @Test
    void givenNonexistentItem_whenFind_throwsException_404() throws Exception {
        // Given

        Map<String,String> jsonRequest = new HashMap<>();
        jsonRequest.put("name","New Test Name");

        // When
        when(itemRepository.findById(0))
            .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND,"No item found with that id."));

        // Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,() -> itemController.updateItem(0, jsonRequest));
        assertEquals("404 NOT_FOUND \"No item found with that id.\"",exception.getMessage());
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

    @Test
    void givenNonexistentItem_whenDelete_throwsException_404() throws Exception {
        // Given

        // When
        when(itemRepository.findById(0))
            .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND,"No item found with that id."));

        // Then
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,() -> itemController.deleteItem(0));
        assertEquals("404 NOT_FOUND \"No item found with that id.\"",exception.getMessage());
    }
}