// com/example/menu/item/InMemoryItemRepository.java

package com.example.menu.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryItemRepository extends CrudRepository<Item, Long> {}