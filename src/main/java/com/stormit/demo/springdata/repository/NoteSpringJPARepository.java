package com.stormit.demo.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import  com.stormit.demo.springdata.entitymanager.Note;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteSpringJPARepository extends JpaRepository<Note, Integer> {

	Optional<Note> findByName(String name);

	Optional<Note> findByNameAndId(String name, Integer id);

	List<Note> findAllByContent(String content);

	@Query("select note from Note note where mod(note.id, 2) = 0 and note.id > :id")
	List<Note> findNotesWithEvenIds(@Param("id") int id);
}
