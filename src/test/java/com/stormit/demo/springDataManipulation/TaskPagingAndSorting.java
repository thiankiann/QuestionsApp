package com.stormit.demo.springDataManipulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TaskPagingAndSorting {

    @Autowired
    private NoteRepository noteRepository;

    @BeforeEach
    void setUp() {
        noteRepository.deleteAll();
    }

    @Test
    void shouldFindObjectsInPages() {

        //given
        populateNotes(10);

        //when
        Page<Note> page0 = noteRepository.findAll(PageRequest.of(0, 3)); // [1, 2, 3]
        Page<Note> page1 = noteRepository.findAll(PageRequest.of(1, 3)); // [4, 5, 6]
        Page<Note> page2 = noteRepository.findAll(PageRequest.of(2, 3)); // [7, 8, 9]
        Page<Note> page3 = noteRepository.findAll(PageRequest.of(3, 3)); // [10]
        Page<Note> page4 = noteRepository.findAll(PageRequest.of(4, 3)); // []

        //then
        assertThat(page0.getTotalElements()).isEqualTo(10);
        assertThat(page0.getNumberOfElements()).isEqualTo(3);
        assertThat(page0.getSize()).isEqualTo(3);
        assertThat(page0.isEmpty()).isFalse();

        assertThat(page1.getTotalElements()).isEqualTo(10);
        assertThat(page1.getNumberOfElements()).isEqualTo(3);
        assertThat(page1.getSize()).isEqualTo(3);

        assertThat(page2.getNumberOfElements()).isEqualTo(3);
        assertThat(page3.getNumberOfElements()).isEqualTo(1);
        assertThat(page4.getNumberOfElements()).isEqualTo(0);

        List<Note> notes = page0.getContent();
        assertThat(notes).hasSize(3);
        assertThat(notes.get(0).getName()).isEqualTo("Note1");
    }
    @Test
    void shouldSortObjects() {

        //given
        populateNotes(10);

        //when
        List<Note> notes = noteRepository.findAll(
                Sort.by("id")
                    .descending()
                    .and(
                            Sort.by(Sort.Order.asc("content"))
                    )
        );

        //then
        assertThat(notes).hasSize(10);
        assertThat(notes.get(0).getName()).isEqualTo("Note1");
    }

    private void populateNotes( int n) {
        for (int i = 1; i <= n; i++) {
            Note note = new Note();
            note.setName("Note" + 1);
            note.setContent("Note" + i);

            noteRepository.save(note);
        }
    }
}
