package com.stormit.demo.springdata.relations;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;
import java.util.UUID;

//@Entity
public class Actor {

  //  @Id
    private UUID id;

    private String name;

   // @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies;

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }
}
