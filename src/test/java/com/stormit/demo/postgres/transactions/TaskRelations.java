package com.stormit.demo.postgres.transactions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.stormit.demo.postgres.relations.Car;
import com.stormit.demo.postgres.relations.Engine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TaskRelations {

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldCreateOneToOneBidirectionalRelation() {
        // given
        Car car = new Car("Audi A8");
        car.setEngine(new Engine());

        // when
        entityManager.persist(car);

        // then
        car = entityManager.find(Car.class, car.getId());

        assertThat(car).isNotNull();
        assertThat(car.getEngine()).isNotNull();
        assertThat(car.getEngine().getCar()).isNotNull();
        assertThat(car.getEngine().getCar().getId()).isEqualTo(car.getId());
    }
}
