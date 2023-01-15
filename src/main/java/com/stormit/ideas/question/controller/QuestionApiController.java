package com.stormit.ideas.question.controller;

import com.stormit.ideas.question.service.QuestionService;
import com.stormit.ideas.question.model.Question;
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

    private final QuestionService questionService;

    public QuestionApiController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    List<Question> getQuestions(){
        return questionService.getQuestions();
    }
    @GetMapping("{id}")
    Question getQuestion(@PathVariable UUID id){
        return questionService.getQuestion(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Question createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Question updateQuestion(@PathVariable UUID id, Question question){
        return questionService.updateQuestion(id,question);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestion(@PathVariable UUID id){
        questionService.deleteQuestion(id);
    }

}
