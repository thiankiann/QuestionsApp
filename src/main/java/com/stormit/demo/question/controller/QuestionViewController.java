package com.stormit.demo.question.controller;

import com.stormit.demo.question.model.Question;
import com.stormit.demo.question.service.AnswerService;
import com.stormit.demo.question.service.QuestionService;
import com.stormit.demo.category.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/questions")
public class QuestionViewController {

    public QuestionService questionService;
    public AnswerService answerService;
    public CategoryService categoryService;
    //public Question question;


    public QuestionViewController(QuestionService questionService, CategoryService categoryService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
    }


   /* public QuestionViewController(QuestionService questionService, Question question) {
        this.questionService = questionService;
        this.question = question;
    }*/

    @RequestMapping
    public String indexView(Model model){
        model.addAttribute("questions", questionService.getQuestions());
        model.addAttribute("categories", categoryService.getCategories(Pageable.unpaged()));
       return "question/index";
    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id){
        model.addAttribute("question", questionService.getQuestion(id));
        model.addAttribute("answer", answerService.getAnswers(id));
        model.addAttribute("categories", categoryService.getCategories(Pageable.unpaged()));
        return "question/single";
    }

    @GetMapping("add")
    public String addView(Model model){
        model.addAttribute("question", new Question());

        return "question/add";
    }

    @PostMapping
    public String add(Question question){
        questionService.createQuestion(question);

        return "redirect:/questions";
    }
}
