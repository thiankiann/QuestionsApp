package com.stormit.demo.postgres.transactions;

import com.stormit.demo.postgres.entitymanager.Note;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TaskNativeQuery<EntityManager> {

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


}
