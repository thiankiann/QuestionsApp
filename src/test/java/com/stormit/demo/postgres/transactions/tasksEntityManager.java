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
    @Test
    void shouldUpdateOneRecord() {
        //given
        Note note1 = new Note(1, "Note 1", "Note 1 - content");
        Note note2 = new Note(2, "Note 2", "Note 2 - content");
        entityManager.persist(note1);
        entityManager.persist(note2);

        //when
        note1.setContent("Updated.");
        entityManager.merge(note1);

        //then
        Long count = entityManager.createQuery("select count(n) from Note n where n.content = 'Updated.'",
                Long.class).getSingleResult();
        assertThat(count).isEqualTo(1);
    }

    @Test
    void shouldFollowEntityLifeCycle() {
        //transient
        Note note = new Note(1,"Note","Note content");
        assertThat(entityManager.contains(note)).isFalse();

        note.setContent("Updated 1");
        entityManager.flush();
        assertThat(entityManager.find(Note.class, 1)).isNull();

        //managed
        entityManager.persist(note);
        assertThat(entityManager.contains(note)).isTrue();

        note.setContent("Updated 2");
        entityManager.merge(note);
        assertThat(entityManager.find(Note.class,1).getContent()).isEqualTo("Updated 2");

        note.setContent("Updated 3");
        entityManager.flush();  // we shouldn't do it manulally!!
        assertThat(entityManager.find(Note.class, 1).getContent()).isEqualTo("Updated 3");

        // detached
        entityManager.detach(note);
        note.setContent("Updated 4");
        entityManager.flush(); // we should not do it manually!!
        assertThat(entityManager.find(Note.class, 1).getContent()).isEqualTo("Updated 3");

        note = entityManager.merge(note);
        assertThat(entityManager.find(Note.class,1).getContent()).isEqualTo("Updated 4");

        //managed again
        //removed
        entityManager.remove(note);
        assertThat(entityManager.find(Note.class, 1)).isNull();
    }
}
