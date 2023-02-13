package com.stormit.demo.springDataManipulation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    @Transactional
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Transactional(readOnly = true)
    public List<Note> findAll() {
        return noteRepository.findAll();
    }
}
