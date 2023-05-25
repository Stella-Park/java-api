// com/example/menu/item/InMemoryItemRepository.java

package com.example.todos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryTodoRepository extends CrudRepository<Todo, Long> {}