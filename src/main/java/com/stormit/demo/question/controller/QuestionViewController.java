package com.stormit.demo.question.controller;

import com.stormit.demo.IdeasConfiguration;
import com.stormit.demo.category.service.CategoryService;
import com.stormit.demo.question.model.Question;
import com.stormit.demo.question.service.AnswerService;
import com.stormit.demo.question.service.QuestionService;
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

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CategoryService categoryService;
    private final IdeasConfiguration ideasConfiguration;


    public QuestionViewController(QuestionService questionService, AnswerService answerService, CategoryService categoryService, IdeasConfiguration ideasConfiguration) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.categoryService = categoryService;
        this.ideasConfiguration = ideasConfiguration;
    }


   /* public QuestionViewController(QuestionService questionService, Question question) {
        this.questionService = questionService;
        this.question = question;
    }*/

    @GetMapping
    public String indexView(Model model){
        model.addAttribute("questions", questionService.getQuestions());
        //addGlobalAttributes(model);

        return "question/index";
    }
    //   System.out.println("id = " + question.getId() );
    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id){
        model.addAttribute("question", questionService.getQuestion(id));
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
