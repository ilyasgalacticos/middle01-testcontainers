package kz.bitlab.middle01.middle01testcontainers;

import jakarta.persistence.EntityNotFoundException;
import kz.bitlab.middle01.middle01testcontainers.model.Item;
import kz.bitlab.middle01.middle01testcontainers.repository.ItemRepository;
import kz.bitlab.middle01.middle01testcontainers.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    void createItemTest() {

        Item item = new Item(1L, "XIAOMI", 10, 200);
        when(itemRepository.save(any(Item.class))).thenReturn(item);
        Item createdItem = itemService.createItem(item);

        assertNotNull(createdItem);
        assertEquals(item.getId(), createdItem.getId());
        assertEquals(item.getName(), createdItem.getName());
        assertEquals(item.getPrice(), createdItem.getPrice());
        assertEquals(item.getAmount(), createdItem.getAmount());

        verify(itemRepository.findAll());
    }

    @Test
    void getItemByIdTest() {
        Long id = 777L;
        Item item = new Item(id, "XIAOMI", 10, 200);
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));
        Optional<Item> optional = itemService.getItemById(id);
        assertTrue(optional.isPresent());
        assertEquals(item, optional.get());
    }

    @Test
    void getAllItemsTest() {
        List<Item> itemList = Arrays.asList(
                new Item(1L, "XIAOMI", 10, 200),
                new Item(2L, "SAMSUNG", 20, 800)
        );

        when(itemRepository.findAll()).thenReturn(itemList);
        List<Item> items = itemService.getAllItems();

        assertNotNull(items);
        assertEquals(2, items.size());
        verify(itemRepository).findAll();

    }

    @Test
    void deleteItemByIdShouldSuccessfullyDeleteWhenItemExists() {
        Long id = 1L;
        when(itemRepository.existsById(id)).thenReturn(true);
        itemService.deleteItemById(id);
        verify(itemRepository).deleteById(id);
    }

    @Test
    void deleteItemByIdShouldThrowExceptionWHenItemNotFound() {
        Long id = 1L;
        when(itemRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class, () -> itemService.deleteItemById(id)
        );
        assertEquals("Item not found by id: " + id, thrown.getMessage());
    }

}
