package com.stormit.demo.postgres.transactions;

import com.stormit.demo.postgres.entitymanager.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
@Transactional
public class tasksEntityManager {
    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void cleanUp() {
        entityManager.createQuery("delete from Note").executeUpdate();
    }

    @Test
    void shouldAddTwoRecords() {
        // given
        Note note1 = new Note();
        note1.setId(1);
        note1.setName("Note 1");
        note1.setContent("Note 1 – content");

        Note note2 = new Note();
        note2.setId(2);
        note2.setName("Note 2");
        note2.setContent("Note 2 – content");

        // when
        entityManager.persist(note1);
        entityManager.persist(note2);

        // then
        Long count = entityManager.createQuery("select count(n) from Note n", Long.class)
                .getSingleResult();
        assertThat(count).isEqualTo(2);
    }

}
