package com.stormit.demo.postgres.dao;

import com.stormit.demo.postgres.entitymanager.Note;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class NoteDaoImpl implements NoteDao{

    private final EntityManager em;

    public NoteDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Note create(Note note) {
        em.persist(note);
        return note;
    }
    @Override
    public Note read(Integer id) {
        return em.find(Note.class, id);
    }
    @Override
    public Note update(Note note) {
        return em.merge(note);
    }
    @Override
    public void delete(Integer id) {
        em.remove(em.find(Note.class, id));
    }
}
