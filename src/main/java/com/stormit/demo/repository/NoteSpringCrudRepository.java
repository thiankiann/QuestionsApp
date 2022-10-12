package com.stormit.demo.repository;

import com.stormit.demo.postgres.entitymanager.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteSpringCrudRepository extends CrudRepository<Note,Integer> {
}
