package com.stormit.ideas.springdata;

import com.stormit.ideas.springdata.dao.NoteDao;
import com.stormit.ideas.springdata.entitymanager.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TaskDao {

    @Autowired
    private NoteDao noteDao;

    @Test
    void shouldUseDao() {
        //given
        Note note = new Note(1, "Note1", "Note1");

        //when
        note = noteDao.create(note);

        // then
        assertThat(note).isNotNull();
        assertThat(note.getId()).isEqualTo(1);
        assertThat(note.getName()).isEqualTo("Note1");
    }
}
