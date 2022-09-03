package com.stormit.demo.postgres.transactions;

import com.stormit.demo.postgres.entitymanager.Note;
import com.stormit.demo.postgres.relations.Actor;
import com.stormit.demo.postgres.relations.Category;
import com.stormit.demo.postgres.relations.Movie;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TaskNativeQuery {

    @Autowired
    private EntityManager entityManager;

    @ParameterizedTest
    @ValueSource(strings = {
            "select * from Note note",
            "select * from Note as note",
            "select id, name, content from Note note",
            "select id, name, content from Note"
    })
    void shouldSelectListOfObjects(String sqlString) {
        // given
        entityManager.persist(new Note(1, "Note1", "Note 1"));
        entityManager.persist(new Note(2, "Note2", "Note 2"));

        // when
        Query query = entityManager.createNativeQuery(sqlString, Note.class);
        List<Note> resultList = query.getResultList();

        // then
        assertThat(resultList).hasSize(2);
        assertThat(resultList)
                .extracting(Note::getName)
                .containsExactlyInAnyOrder("Note1", "Note2");
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "select * from Note note where id=1",
            "select * from Note note where note.name = 'Note1'",
            "select * from Note note where name like '%te1%'"
    })
    void shouldFilterObjects(String sqlString) {
        // given
        entityManager.persist(new Note(1, "Note1", "Note 1"));
        entityManager.persist(new Note(2, "Note2", "Note 2"));

        // when
        Query query = entityManager.createNativeQuery(sqlString, Note.class);
        List<Note> resultList = query.getResultList();

        // then
        assertThat(resultList).hasSize(1);
        assertThat(resultList.get(0).getName()).isEqualTo("Note1");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "select distinct c.name " +
                    "from Movie m " +
                    "join Category c on (m.category_id = c.id)" +
                    "where m.name = 'Rambo'",

            "select distinct c.name " +
                    "from Movie m " +
                    "join Category c on (m.category_id = c.id) " +
                    "join Movie_Actors ma on (ma.movies_id = m.id) " +
                    "join Actor a on (ma.actors_id = a.id) " +
                    "where a.name like 'Tomasz%'"
    })

    void shouldJoinTables(String sqlString) {
        // given
        initMovies();

        // when
        Query query = entityManager.createNativeQuery(sqlString);
        List<String> resultList = query.getResultList();

        // then
        assertThat(resultList).hasSize(1);
        assertThat(resultList.get(0)).isEqualTo("Thriller");
    }
    private void initMovies() {
        Movie movie1 = new Movie("Rambo");
        Movie movie2 = new Movie("Rocky");

        Category category1 = new Category("Thriller");
        Category category2 = new Category("Comedy");

        Actor actor1 = new Actor("Tomasz Woliński");
        Actor actor2 = new Actor("Sylvester Stallone");

        movie1.setActors(Set.of(actor1, actor2));
        movie1.setCategory(category1);
        movie2.setCategory(category2);

        entityManager.persist(category1);
        entityManager.persist(category2);

        entityManager.persist(actor1);
        entityManager.persist(actor2);

        entityManager.persist(movie1);
        entityManager.persist(movie2);
    }
}
