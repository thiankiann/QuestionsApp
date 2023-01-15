package com.stormit.ideas.springdata.relations;

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
