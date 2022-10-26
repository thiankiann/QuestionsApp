package com.stormit.demo.springdata.transactions;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransactionsExampleService {

    private final NoteService noteService;

    public TransactionsExampleService(NoteService noteService) {
        this.noteService = noteService;
    }

    @Transactional
    public void updateImplicitly(UUID id, String name) {
        NoteTransactions note = noteService.readNote(id);
        note.setName(name);

        // noteService.updateNote(note);  //NOT NEEDED! because we are in active transaction

    }

    @Transactional
    void updateImplicitlyNonPublic(UUID id, String name) {
        NoteTransactions note = noteService.readNote(id);
        note.setName(name);
    }

    @Transactional
    public void updateWithThrowingRuntimeException(UUID id, String name) {
        NoteTransactions note = noteService.readNote(id);
        note.setName(name);

        throw new CustomRuntimeException();
    }

    @Transactional
    public void updateWithThrowingException(UUID id, String name) throws Exception {
        NoteTransactions note = noteService.readNote(id);
        note.setName(name);

        throw new CustomException();
    }

    @Transactional(noRollbackFor = CustomRuntimeException.class)
    public void updateWithRollbackFor(UUID id, String name) {
        NoteTransactions note = noteService.readNote(id);
        note.setName(name);

        throw new CustomRuntimeException();
    }

    @Transactional(rollbackFor = CustomException.class)
    public void updateWithRollback(UUID id, String name) throws Exception {
        NoteTransactions note = noteService.readNote(id);
        note.setName(name);

        throw new CustomException();
    }

    private class CustomRuntimeException extends RuntimeException{
    }

    private class CustomException extends Exception {
    }
}
