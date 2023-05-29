// com/example/menu/item/Item.java

package com.example.todos;

import org.springframework.data.annotation.Id;

public class Todo {
    private final Long id;
    private final String title;
    private final Boolean isCompleted;

    public Todo(
        Long id,
        String title,
        Boolean isCompleted
    ) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
    }

	@Id
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public Todo updateWith(Todo item) {
        return new Todo(
            this.id,
            item.title,
            item.isCompleted
        );
    }
}