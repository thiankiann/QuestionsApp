package com.stormit.demo.controller;

import com.stormit.demo.model.Answer;
import com.stormit.demo.model.Question;
import com.stormit.demo.service.AnswerService;
import com.stormit.demo.service.QuestionService;
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
@RequestMapping("api/v1/questions/{questions-id}/answers")
public class AnswerController {
    private AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    List<Answer> getAnswers(@PathVariable("questions-id") UUID questionId){
        return answerService.getAnswers(questionId);
    }
    @GetMapping("{id}")
    Answer getAnswer(@PathVariable("question-id") UUID questionId, @PathVariable("answer-id") UUID answerId){
        return answerService.getAnswer(answerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Answer createAnswer(@PathVariable("question-id") UUID questionId , @RequestBody Answer answer){
        return answerService.createAnswer(questionId, answer);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Answer updateAnswer(@PathVariable("question-id") UUID questionId ,@PathVariable("answer-id") UUID answerId,
                        @RequestBody Answer answer){
        return answerService.updateAnswer(answerId,answer);
    }

    @DeleteMapping("{answer-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestion(@PathVariable("answer-id") UUID answerId){
        answerService.deleteQuestion(answerId);
    }


}
