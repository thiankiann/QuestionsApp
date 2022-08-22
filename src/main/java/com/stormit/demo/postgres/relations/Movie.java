package com.stormit.demo.postgres.relations;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;
import java.util.UUID;

@Entity
public class Movie {
    @Id
    private UUID id;

    private String name;

    @ManyToMany
    private Set<Actor> actors;

    @ManyToOne
    private Category category;

    public Movie() {
    }
    public Movie(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
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

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
