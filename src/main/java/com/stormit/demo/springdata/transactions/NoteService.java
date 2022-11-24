package com.stormit.demo.springdata.transactions;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Service
public class NoteService {

    private final EntityManager entityManager;

    public NoteService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public NoteTransactions createNote(NoteTransactions note) {
        entityManager.persist(note);
        return note;
    }
    @Transactional(readOnly = true)
    public List<NoteTransactions> findAll() {
        return entityManager
                .createQuery("from NoteTransactions",NoteTransactions.class)
                .getResultList();
    }
    @Transactional(readOnly = true)
    public Optional<NoteTransactions> findByName(String name) {
        return entityManager
                .createQuery("from NoteTransactions note where note.name = :name", NoteTransactions.class)
                .setParameter("name", name)
                .getResultList()
                .stream().findAny();
    }
    @Transactional
    public void removeAll() {
        entityManager.createQuery("delete from NoteTransactions")
                .executeUpdate();
    }
    @Transactional
    public NoteTransactions updateNote(NoteTransactions note) {
        entityManager.merge(note);   //merge() - like persist but on entity which already exist
        return note;
    }

    public NoteTransactions readNote(UUID id) {
        return entityManager.find(NoteTransactions.class,id);
    }
}
