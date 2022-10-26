package com.stormit.demo.controller;

import com.stormit.demo.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/questions")
public class QuestionViewController {

    public QuestionService questionService;

    public QuestionViewController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @RequestMapping
    public String indexView(Model model){
        model.addAttribute("questions", questionService.getQuestions());

        return "question/index";
    }

    @GetMapping("{id}")
    public String singleView(@PathVariable UUID id){
        return "question/single";
    }
    @GetMapping("add")
    public String addView(){
        return "question/add";
    }
}
