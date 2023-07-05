package com.serraabak.pandorahouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @Test
    void givenItems_whenGetAllItems_thenReturnItems() throws Exception {
        // Given
        List<Item> items = new ArrayList<Item>() {{
            add(new Item("example1",ItemType.TSHIRT,100.00,1,"example1.jpg"));
            add(new Item("example2",ItemType.PANTS,200.00,2,"example2.jpg"));
            add(new Item("example2",ItemType.SHOES,40.00,3,"example3.jpg"));
        }};
        ResponseEntity<List<Item>> expected = new ResponseEntity<List<Item>>(items, HttpStatus.OK);
        

        // When
        when(itemRepository.findAll()).thenReturn(items);
        ResponseEntity<?> response = itemController.getAllItems();
        
        // Then
        verify(itemRepository).findAll();
        assertEquals(expected, response);
    }

    @Test
    void givenItems_whenAddItem_thenReturnSuccess() throws Exception {
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
}