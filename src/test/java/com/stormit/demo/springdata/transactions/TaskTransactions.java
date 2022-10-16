package com.stormit.demo.springdata.transactions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TaskTransactions {

    @Autowired
    private TransactionsExampleService transactionsExampleService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private TransactionHelper transactionHelper;

    private UUID id;

    @BeforeEach
    void setUp() {
        noteService.removeAll();

        NoteTransactions note = new NoteTransactions("Note1");
        id = note.getId();

        noteService.createNote(note);
    }

    @Test
    public void shouldUpdateImplicitly(){
        //given

        //when
        transactionsExampleService.updateImplicitly(id, "updated");

        //then
        Optional<NoteTransactions> updateNote = noteService.findByName("updated");
        assertThat(updateNote).isPresent();
    }
    @Test
    public void shouldNotUpdateNonPublic() {
        //given

        //when
        transactionsExampleService.updateImplicitlyNonPublic(id, "updated");

        //then
        Optional<NoteTransactions> updatedNote = noteService.findByName("updated");
        assertThat(updatedNote).isNotPresent();
    }

}
