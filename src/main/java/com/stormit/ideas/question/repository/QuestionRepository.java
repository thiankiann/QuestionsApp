package com.stormit.ideas.question.repository;

import com.stormit.ideas.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question,UUID> {

    List<Question> findAllByCategoryId(UUID id);
}
