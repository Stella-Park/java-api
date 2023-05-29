// com/example/menu/item/Item.java

package com.example;

import org.springframework.data.annotation.Id;

public class TodoApplication {
    private final Long id;
    private final String title;
    private final Boolean isCompleted;

    // todo list -> id, title, completed
    // 기존 -> id, name, price, description, image

    public TodoApplication(
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

    public TodoApplication updateWith(TodoApplication item) {
        return new TodoApplication(
            this.id,
            item.title,
            item.isCompleted
        );
    }
}