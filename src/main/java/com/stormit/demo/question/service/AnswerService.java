package com.stormit.demo.question.service;

import com.stormit.demo.question.model.Answer;
import com.stormit.demo.question.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AnswerService {

    public AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAnswers(UUID questionId) {
        return Arrays.asList( new Answer("Answer 1"), new Answer("Answer 2"));
    }

    public Answer getAnswer(UUID questionId) {
        return answerRepository.findByQuestionId(questionId) ;
    }

    public Answer createAnswer(UUID questionId, Answer answer) {
        return null;
    }

    public Answer updateAnswer(UUID answerId, Answer answer) {
        return null;
    }

    public void deleteQuestion(UUID answerId) {
    }
}
