package com.stormit.ideas.springdata.dao;

import com.stormit.ideas.springdata.entitymanager.Note;

public interface NoteDao {
    Note create(Note note);
    Note read(Integer id);
    Note update(Note note);
    void delete(Integer id);
}
