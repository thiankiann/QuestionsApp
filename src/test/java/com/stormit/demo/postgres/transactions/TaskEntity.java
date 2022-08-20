package com.stormit.demo.postgres.transactions;

import com.stormit.demo.postgres.entity.NoteWithGeneratedIdSequence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

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



}
