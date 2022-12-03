package com.stormit.demo.question.controller;

import com.stormit.demo.question.service.QuestionsService;
import com.stormit.demo.question.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * CRUD implementation:
 * C -create
        ->POST/questions

 * R -read
        ->GET/questions       - for all questions
        ->GET/questions/[id]  - for single question

 * U -update
        ->PUT/questions/[id]

 * D -delete
        ->DELETE/questions/[id]
 */
@RestController
@RequestMapping("api/v1/questions")
public class QuestionApiController {

    private final QuestionsService questionsService;

    public QuestionApiController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping
    List<Question> getQuestions(){
        return questionsService.getQuestions();
    }
    @GetMapping("{id}")
    Question getQuestion(@PathVariable UUID id){
        return questionsService.getQuestion(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Question createQuestion(@RequestBody Question question){
        return questionsService.createQuestion(question);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Question updateQuestion(@PathVariable UUID id, Question question){
        return questionsService.updateQuestion(id,question);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestion(@PathVariable UUID id){
        questionsService.deleteQuestion(id);
    }

}
