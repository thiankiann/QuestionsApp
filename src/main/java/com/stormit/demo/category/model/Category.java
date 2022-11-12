package com.stormit.demo.category.model;

import java.util.UUID;

public class Category {

    UUID id;
    String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
