// com/example/menu/item/ItemController.java

package com.example.todos;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/todos")
public class ItemController {
    private final TodoService service;

    public ItemController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        List<Todo> items = service.findAll();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> find(@PathVariable("id") Long id) {
        Optional<Todo> item = service.find(id);
        return ResponseEntity.of(item);
    }

    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Todo item) {
        Todo created = service.create(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(
        @PathVariable("id") Long id,
        @RequestBody Todo updatedItem) {

        Optional<Todo> updated = service.update(id, updatedItem);

        return updated
            .map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> {
                Todo created = service.create(updatedItem);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(created.getId())
                    .toUri();
                return ResponseEntity.created(location).body(created);
            });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}