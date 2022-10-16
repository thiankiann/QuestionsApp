package com.stormit.demo.springdata.dao;

import com.stormit.demo.springdata.entitymanager.Note;

public interface NoteDao {
    Note create(Note note);
    Note read(Integer id);
    Note update(Note note);
    void delete(Integer id);
}
