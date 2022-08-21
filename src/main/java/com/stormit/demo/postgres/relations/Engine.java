package com.stormit.demo.postgres.relations;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Engine {
    @Id
    private UUID id;

    @OneToOne(mappedBy = "engine")
    private Car car;

    public Engine() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
