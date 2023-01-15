package com.stormit.ideas.springdata;

import com.stormit.ideas.springdata.entity.NoteWithGeneratedIdSequence;
import com.stormit.ideas.springdata.entity.NoteWithGeneratedIdTable;
import com.stormit.ideas.springdata.entity.NoteWithUUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskEntity {

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldGeneratePrimaryKeyWithSequence() {

        //given
        NoteWithGeneratedIdSequence note = new NoteWithGeneratedIdSequence("Note","Note context") ;

        //when
        entityManager.persist(note);

        //then
        assertThat(note.getId()).isNotNull();

    }
    @Test
    void shouldGeneratePrimaryKeyWithSequenceTable() {
        // given
        NoteWithGeneratedIdTable note = new NoteWithGeneratedIdTable("Note", "Note content");

        // when
        entityManager.persist(note);

        // then
        assertThat(note.getId()).isNotNull();
    }
    @Test
    void shouldUseUUIDForPrimaryKey() {
        // given
        NoteWithUUID note = new NoteWithUUID("Note", "Note content");

        // when
        entityManager.persist(note);

        // then
        assertThat(note.getId()).isNotNull();
    }
}
