package com.stormit.demo.springdata.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class NoteWithGeneratedIdTable {
    //@Id
    //@GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String name;

    private String content;

    public NoteWithGeneratedIdTable() {
    }

    public NoteWithGeneratedIdTable(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
