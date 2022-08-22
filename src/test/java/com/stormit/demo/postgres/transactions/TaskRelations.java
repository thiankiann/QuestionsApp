package com.stormit.demo.postgres.transactions;
import com.stormit.demo.postgres.relations.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.Set;

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

    void complexExample() {
        //given
        Movie movie1 = new Movie("Rambo");
        Movie movie2 = new Movie("Rocky");

        Category category1 = new Category("Thriller");
        Category category2 = new Category("Comedy");

        Actor actor1 = new Actor("Brad Pitt");
        Actor actor2 = new Actor("Sylvester Stalone");

        movie1.setActors(Set.of(actor1,actor2));
        movie1.setCategory(category1);
        movie2.setCategory(category2);

        //when
        entityManager.persist(category1);
        entityManager.persist(category2);

        entityManager.persist(actor1);
        entityManager.persist(actor2);

        entityManager.persist(movie1);
        entityManager.persist(movie2);

        //then
        movie1 = entityManager.find(Movie.class, movie1.getId());
        movie2 = entityManager.find(Movie.class, movie2.getId());

        assertThat(movie1.getActors()).hasSize(2);
        assertThat(movie2.getActors()).isNull();

    }
}
