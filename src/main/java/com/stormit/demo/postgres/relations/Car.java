package com.stormit.demo.postgres.relations;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
public class Car {
    @Id
    private UUID id;

    private String name;

    @OneToOne
    private Engine engine;

    public Car(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public Car() {
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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
        engine.setCar(this);
    }
}
