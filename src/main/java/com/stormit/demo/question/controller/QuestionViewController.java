package com.stormit.demo.question.controller;

import com.stormit.demo.question.model.Question;
import com.stormit.demo.question.service.QuestionsService;
import com.stormit.demo.category.service.CategoryService;
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

    public QuestionsService questionsService;
    public CategoryService categoryService;
    //public Question question;


    public QuestionViewController(QuestionsService questionsService, CategoryService categoryService) {
        this.questionsService = questionsService;
        this.categoryService = categoryService;
    }


   /* public QuestionViewController(QuestionService questionService, Question question) {
        this.questionService = questionService;
        this.question = question;
    }*/

    @RequestMapping
    public String indexView(Model model){
        model.addAttribute("questions", questionsService.getQuestions());
        model.addAttribute("categories", categoryService.getCategories());
        return "template";
      //  return "question/index";
    }
    //   System.out.println("id = " + question.getId() );
    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id){
        model.addAttribute("question", questionsService.getQuestion(id));
        return "question/single";
    }

    @GetMapping("add")
    public String addView(Model model){
        model.addAttribute("question", new Question());

        return "question/add";
    }

    @PostMapping
    public String add(Question question){
        questionsService.createQuestion(question);

        return "redirect:/questions";
    }
}
