package com.stormit.ideas.springdata.relations;

import java.util.Set;
import java.util.UUID;

//@Entity
public class Category {
  //  @Id
    private UUID id;

    private String name;

    //@OneToMany(mappedBy = "category")
    private Set<Movie> movies;

    public Category() {
    }

    public Category(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
