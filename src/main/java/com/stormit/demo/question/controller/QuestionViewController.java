package com.stormit.demo.question.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.stormit.demo.category.service.CategoryService;
import com.stormit.demo.question.domain.model.Question;
import com.stormit.demo.question.service.AnswerService;
import com.stormit.demo.question.service.QuestionService;

import java.util.UUID;

@Controller
@RequestMapping("/questions")
public class QuestionViewController {

	private final QuestionService questionsService;
	private final AnswerService answerService;
	private final CategoryService categoryService;

	public QuestionViewController(QuestionService questionsService,
	                              AnswerService answerService,
	                              CategoryService categoryService) {
		this.questionsService = questionsService;
		this.answerService = answerService;
		this.categoryService = categoryService;
	}

	@GetMapping
	public String indexView(Model model){
		model.addAttribute("questions", questionsService.getQuestions());
		model.addAttribute("categories", categoryService.getCategories(
				PageRequest.of(0, 10, Sort.by("name").ascending())
		));

		return "question/index";
	}

	@GetMapping("{id}")
	public String singleView(Model model, @PathVariable UUID id){
		model.addAttribute("question", questionsService.getQuestion(id));
		model.addAttribute("answers", answerService.getAnswers(id));
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
		questionsService.createQuestion(question);

		return "redirect:/questions";
	}
	@GetMapping("hot")
	public String hotView(
			@RequestParam(name = "page", defaultValue = "1") int page,
			Model model
	){
		PageRequest pageRequest = PageRequest.of(page -1, 2);

		Page<Question> questionsPage = questionsService.findHot(pageRequest);

		model.addAttribute("questionsPage", questionsPage);

		return "/question/index";
	}
}
