package com.stormit.ideas.question.repository;

import com.stormit.ideas.question.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    List<Answer> findByQuestionId(UUID questionId);

}
