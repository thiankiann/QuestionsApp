package com.stormit.demo.question.service;

import com.stormit.demo.category.domain.repository.CategoryRepository;
import com.stormit.demo.question.domain.model.Question;
import com.stormit.demo.question.domain.repository.AnswerRepository;
import com.stormit.demo.question.domain.repository.QuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuestionServiceIT {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetAllQuestions() {

        //given
        questionRepository.deleteAll();

        questionRepository.saveAll(List.of(
                new Question("Question1"),
                new Question("Question2"),
                new Question("Question2")
        ));
        //when
        List<Question> questions = questionService.getQuestions();

        //then
        assertThat(questions)
            .hasSize(3)
            .extracting(Question::getName)
            .containsExactlyInAnyOrder("Question1", "Question2", "Question3");

    }

    @Test
    void getQuestion() {
    }

    @Test
    void shouldCreateQuestion() {
        // given
        Question question = new Question("Question");

        // when
        Question result = questionService.createQuestion(question);

        // then
        assertThat(result.getName()).isEqualTo(question.getName());
       // assertThat(result.getName()).isEqualTo(questionRepository.getById(result.getId()).getName());
    }

    @Test
    void updateQuestion() {
    }

    @Test
    void deleteQuestion() {
    }

    @Test
    void findAllByCategoryId() {
    }

    @Test
    void findHot() {
    }

    @Test
    void findUnanswered() {
    }

    @Test
    void findByQuery() {
    }
}