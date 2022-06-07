package com.stormit.demo.service;

import com.stormit.demo.model.Question;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    public List<Question> getQuestions() {
        return Arrays.asList( new Question("Question 1" ), new Question("Question 2"));
    }

    public Question getQuestions(UUID id) {
        return null;
    }

    public Question createQuestion(Question question) {
        return null;
    }

    public void deleteQuestion(UUID id) {
    }

    public Question updateQuestion(UUID id, Question question) {
        return null;
    }
}
