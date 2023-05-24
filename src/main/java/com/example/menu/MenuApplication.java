// com/example/menu/item/Item.java

package com.example.menu;

import org.springframework.data.annotation.Id;

public class MenuApplication {
    private final Long id;
    private final String name;
    private final Long price;
    private final String description;
    private final String image;

    public MenuApplication(
            Long id,
            String name,
            Long price,
            String description,
            String image
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

	@Id
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public MenuApplication updateWith(MenuApplication item) {
        return new MenuApplication(
            this.id,
            item.name,
            item.price,
            item.description,
            item.image
        );
    }
}