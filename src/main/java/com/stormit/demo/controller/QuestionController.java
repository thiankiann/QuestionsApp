package com.stormit.demo.controller;

import com.stormit.demo.service.QuestionService;
import com.stormit.demo.model.Questions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @GetMapping
    List<Questions> getQuestions(){
        return questionService.getQuestions();
    }
}
