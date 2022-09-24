package com.stormit.demo.postgres.dao;

import com.stormit.demo.postgres.entitymanager.Note;

public interface NoteDao {
    Note create(Note note);
    Note read(Integer id);
    Note update(Note note);
    void delete(Integer id);
}
