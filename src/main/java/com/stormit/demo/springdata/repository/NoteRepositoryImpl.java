package com.stormit.demo.springdata.repository;

import com.stormit.demo.springdata.dao.NoteDao;
import com.stormit.demo.springdata.entitymanager.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteRepositoryImpl implements NoteRepository {

	private final NoteDao noteDao;

	public NoteRepositoryImpl(NoteDao noteDao) {
		this.noteDao = noteDao;
	}



	@Override
	public Note add(Note note) {
		Note newNote = noteDao.create(note);
		// Additional logic

		return newNote;
	}

	@Override
	public Note get(Integer id) {
		Note note = noteDao.read(id);
		// Additional logic
		
		return note;
	}

	@Override
	public Note update(Note note) {
		Note updatedNote = noteDao.update(note);
		// Additional logic

		return updatedNote;
	}

	@Override
	public void remove(Integer id) {
		noteDao.delete(id);
		// Additional logic
	}
}
