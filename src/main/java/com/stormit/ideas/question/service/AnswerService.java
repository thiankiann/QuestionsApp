package com.stormit.ideas.question.service;

import com.stormit.ideas.question.model.Answer;
import com.stormit.ideas.question.model.Question;
import com.stormit.ideas.question.repository.AnswerRepository;
import com.stormit.ideas.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerService {

    public AnswerRepository answerRepository;
    public QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional(readOnly = true)
    public List<Answer> getAnswers(UUID questionId) {
        return answerRepository.findByQuestionId(questionId) ;
    }

    public Answer getAnswer(UUID id) {
        return new Answer("Answer 1" +id ) ;
    }

    @Transactional
    public Answer createAnswer(UUID questionId, Answer answerRequest) {
        Answer answer = new Answer();

        answer.setName(answerRequest.getName());

        Question question = questionRepository.getReferenceById(questionId);
        question.addAnswer(answer);

        return answer;
    }

    public Answer updateAnswer(UUID answerId, Answer answerRequest) {
        Answer answer = answerRepository.getById(answerId);
        answer.setName(answerRequest.getName());

        return answerRepository.save(answer);
    }

    public void deleteQuestion(UUID answerId) {
        answerRepository.deleteById(answerId);
    }
}
