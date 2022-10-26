package com.stormit.demo.postgres;

import com.stormit.demo.springdata.entitymanager.Note;
import com.stormit.demo.springdata.repository.NoteSpringCrudRepository;
import com.stormit.demo.springdata.repository.NoteSpringJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TaskSpringRepository {

     @Autowired
    private NoteSpringCrudRepository noteSpringCrudRepository;

    @Autowired
    private NoteSpringJPARepository noteSpringJPARepository;

        @Test
        void shouldAddNewObject() {
            // given
            Note note = new Note(1, "Note1", "Note 1");

            // when
            note = noteSpringCrudRepository.save(note);

            // then
            note = noteSpringCrudRepository.findById(note.getId())
                    .orElseThrow();

            assertThat(note).isNotNull();
            assertThat(note.getId()).isEqualTo(1);
            assertThat(note.getName()).isEqualTo("Note1");
        }

        @Test
        void shouldReadListObjects() {
            // given
            Note note1 = new Note(1, "Note1", "Note 1 -content");
            Note note2 = new Note(2, "Note2", "Note 2 -content");
            noteSpringCrudRepository.saveAll(Arrays.asList(note1, note2));

            //when
            Iterable<Note> result = noteSpringCrudRepository.findAll();

            //then
            assertThat(result).extracting("name").containsExactlyInAnyOrder("Note1", "Note2");
        }

    @Test
    void shouldSelectObjectWithCustomMethod() {
        // given
        Note note1 = new Note(1, "Note1", "Note 1 – content");
        Note note2 = new Note(2, "Note2", "Note 2 – content");
        noteSpringJPARepository.saveAll(Arrays.asList(note1, note2));

        // when
        Optional<Note> maybeNote = noteSpringJPARepository.findByName("Note2");

        // then
        assertThat(maybeNote).isPresent();
        assertThat(maybeNote.get())
                .extracting("name")
                .isEqualTo("Note2");
    }
}
