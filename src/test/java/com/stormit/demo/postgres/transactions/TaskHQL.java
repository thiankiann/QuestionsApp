package com.stormit.demo.postgres.transactions;

import com.stormit.demo.postgres.entitymanager.Note;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class TaskHQL {

    @Autowired
    private EntityManager entityManager;

    @ParameterizedTest
    @ValueSource(strings = {
            "select note from Note note",
            "select note from Note as note",
            "from Note note",
            "from Note"
    })
    void shouldSelectListIfObjects(String hql) {
        //given
        entityManager.persist(new Note(1, "Note 1", "Note 1"));
        entityManager.persist(new Note(2, "Note 2", "Note 2"));

        // when
        TypedQuery<Note> query = entityManager.createQuery(hql, Note.class);
        List<Note> resultList = query.getResultList();

        //then
        assertThat(resultList).hasSize(2);
        assertThat(resultList)
                .extracting(Note::getName)
                .containsExactlyInAnyOrder("Note 1", "Note 2");
    }

}
