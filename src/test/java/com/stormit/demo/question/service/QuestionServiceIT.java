package com.stormit.demo.question.service;

import com.stormit.demo.category.domain.model.Category;
import com.stormit.demo.category.domain.repository.CategoryRepository;
import com.stormit.demo.question.domain.model.Answer;
import com.stormit.demo.question.domain.model.Question;
import com.stormit.demo.question.domain.repository.AnswerRepository;
import com.stormit.demo.question.domain.repository.QuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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
/*
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
*/
    @Test
    void shouldSingleGetQuestion() {
        //given
        Question question = new Question("Question2");

        questionRepository.saveAll(List.of(
                new Question("Question1"),
                question,
                new Question("Question3")
        ));
        //when
        Question result = questionService.getQuestion(question.getId());

        //then
        assertThat(result.getId()).isEqualTo(question.getId());
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
    void shouldUpdateQuestion() {
        //given
        Question question = new Question("Question");
        question = questionService.createQuestion(question);

        question.setName("updated");

        //when
        Question result = questionService.updateQuestion(question.getId(),question);

        //then
        assertThat(result.getId()).isEqualTo(question.getId());
        assertThat(result.getId()).isEqualTo(questionRepository.getReferenceById(question.getId()).getId());
    }

    @Test
    void shouldDeleteQuestion() {
        //given
        Question question = new Question("Question");
        question = questionService.createQuestion(question);
        UUID id = question.getId();

        //when
        Throwable throwable = catchThrowable(() -> questionService.deleteQuestion(id));

        //then
        assertThat(questionRepository.findById(question.getId())).isEmpty();
    }

    @Test
    void shouldFindAllByCategoryId() {
        // given
        Category category = new Category("Category1");
        categoryRepository.save(category);

        Question question1 = new Question("Question1");
        question1.setCategory(category);

        Question question2 = new Question("Question2");
        question2.setCategory(category);

        Question question3 = new Question("Question3");
        questionRepository.saveAll(List.of(question1, question2, question3));

        // when
        List<Question> questions = questionService.findAllByCategoryId(category.getId());

        // then
        assertThat(questions)
                .hasSize(2)
                .extracting(Question::getName)
                .containsExactlyInAnyOrder("Question1", "Question2");
    }
/*
    @Test
    void shouldFindHot() {
        // given
        questionRepository.deleteAll();

        Question question1 = new Question("Question1");
        Question question2 = new Question("Question2");
        Question question3 = new Question("Question3");

        questionRepository.saveAll(List.of(question1, question2, question3));

        Answer answer = new Answer("Answer");
        question2.addAnswer(answer);
        answerRepository.save(answer);

        // when
        Page<Question> result = questionService.findHot(Pageable.unpaged());

        // then
        assertThat(result)
                .hasSize(3)
                .extracting(Question::getName)
                .containsExactlyInAnyOrder("Question2", "Question1", "Question3");
    }
*/
    @Test
    void shouldFindUnanswered() {
        // given
        questionRepository.deleteAll();

        Question question1 = new Question("Question1");
        Question question2 = new Question("Question2");
        Question question3 = new Question("Question3");

        questionRepository.saveAll(List.of(question1, question2, question3));

        Answer answer = new Answer("Answer");
        question2.addAnswer(answer);
        answerRepository.save(answer);

        // when
        Page<Question> result = questionService.findUnanswered(Pageable.unpaged());

        // then
        assertThat(result)
                .hasSize(2)
                .extracting(Question::getName)
                .containsExactlyInAnyOrder("Question1", "Question3");
    }

    @Test
    void shouldFindByQuery() {
        // given
        String query = "abc";

        Question question1 = new Question("Question1");
        Question question2 = new Question("Question2-" + query);
        Question question3 = new Question("Question3");

        questionRepository.saveAll(List.of(question1, question2, question3));

        // when
        Page<Question> result = questionService.findByQuery(query, Pageable.unpaged());

        // then
        assertThat(result)
                .hasSize(1)
                .extracting(Question::getId)
                .containsExactlyInAnyOrder(question2.getId());
    }
}