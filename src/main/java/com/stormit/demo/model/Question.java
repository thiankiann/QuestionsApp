package com.stormit.demo.model;

import java.util.UUID;

public class Question {

    private String name;
    private UUID id;

    public Question() {
    }

    public Question(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
