package com.stormit.demo.model;

public class Question {

    String name;

    public Question() {
    }

    public Question(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "name='" + name + '\'' +
                '}';
    }
}
