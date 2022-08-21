package com.stormit.demo.postgres.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class NoteWithUUID {

    @Id
    private UUID id;

    private String name;

    private String content;

    public NoteWithUUID() {
    }

    public NoteWithUUID(String name, String content) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
