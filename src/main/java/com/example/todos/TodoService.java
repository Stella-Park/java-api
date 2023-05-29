// com/example/menu/item/ItemService.java

package com.example.todos;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableMapRepositories
public class TodoService {
    private final CrudRepository<Todo, Long> repository;

    public TodoService(CrudRepository<Todo, Long> repository) {
        this.repository = repository;
        this.repository.saveAll(defaultItems());
    }

    private static List<Todo> defaultItems() {
        return List.of(
            new Todo(1L, "밥먹기", false),
            new Todo(2L, "일하기", false),
            new Todo(3L, "잠자기", true)
        );
    }

    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<>();
        Iterable<Todo> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Todo> find(Long id) {
        return repository.findById(id);
    }

    public Todo create(Todo item) {
        // To ensure the item ID remains unique,
        // use the current timestamp.
        Todo copy = new Todo(
            new Date().getTime(),
            item.getTitle(),
            item.getIsCompleted()
        );
        return repository.save(copy);
    }

    public Optional<Todo> update( Long id, Todo newItem) {
        // Only update an item if it can be found first.
        return repository.findById(id)
            .map(oldItem -> {
                Todo updated = oldItem.updateWith(newItem);
                return repository.save(updated);
            });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}