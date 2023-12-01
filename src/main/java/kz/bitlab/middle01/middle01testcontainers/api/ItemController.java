package kz.bitlab.middle01.middle01testcontainers.api;

import kz.bitlab.middle01.middle01testcontainers.model.Item;
import kz.bitlab.middle01.middle01testcontainers.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getItems(){
        return itemService.getAllItems();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> getItem(@PathVariable(name = "id") Long id){
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        Item createdItem = itemService.createItem(item);
        return ResponseEntity.ok(createdItem);
    }

}