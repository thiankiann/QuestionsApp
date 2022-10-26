package com.stormit.demo.springdata.repository;

import com.stormit.demo.springdata.entitymanager.Note;

public interface NoteRepository {

	Note add(Note note);

	Note get(Integer id);

	Note update(Note note);

	void remove(Integer id);
}
