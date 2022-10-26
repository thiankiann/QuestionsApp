package com.stormit.demo.springdata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import  com.stormit.demo.springdata.entitymanager.Note;

@Repository
public interface NoteSpringCrudRepository extends CrudRepository<Note, Integer> {
}
