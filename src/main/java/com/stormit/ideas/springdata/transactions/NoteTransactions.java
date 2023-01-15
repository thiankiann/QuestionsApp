package com.stormit.ideas.springdata.transactions;

import java.util.UUID;

//@Entity
public class NoteTransactions {

   // @Id
    private UUID id;

    //@Column(nullable = false)   // this column won't be null
    private String name;

    public NoteTransactions() {
    }

    public NoteTransactions(String name) {
        this.id = UUID.randomUUID();
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
