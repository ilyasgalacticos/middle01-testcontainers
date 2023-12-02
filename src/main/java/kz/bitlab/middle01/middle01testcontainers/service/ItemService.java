package kz.bitlab.middle01.middle01testcontainers.service;

import jakarta.persistence.EntityNotFoundException;
import kz.bitlab.middle01.middle01testcontainers.model.Item;
import kz.bitlab.middle01.middle01testcontainers.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item createItem(Item item){
        Item createdItem = itemRepository.save(item);
        log.info("Created new Item {}", createdItem);
        return createdItem;
    }

    public Optional<Item> getItemById(Long id){
        Optional<Item> item = itemRepository.findById(id);
        item.ifPresentOrElse(
                it -> log.info("Retreived Item with id {}", id, it),
                () -> log.warn("Item not found with id {}", id)
        );
        return item;
    }

    public List<Item> getAllItems(){
        List<Item> items = itemRepository.findAll();
        log.info("Items retrieved with size {}", items.size());
        return items;
    }

    public void deleteItemById(Long id){
        if(!itemRepository.existsById(id)){
            throw new EntityNotFoundException("Item not found by id: " + id);
        }
        itemRepository.deleteById(id);
    }
}
