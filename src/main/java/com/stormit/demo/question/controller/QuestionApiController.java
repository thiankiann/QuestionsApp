package com.stormit.demo.question.controller;

import com.stormit.demo.question.domain.model.Question;
import com.stormit.demo.question.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionApiController {

	private final QuestionService questionsService;

	public QuestionApiController(QuestionService questionsService) {
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
	Question updateQuestion(@PathVariable UUID id, @RequestBody Question question){
		return questionsService.updateQuestion(id, question);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	void deleteQuestion(@PathVariable UUID id){
		questionsService.deleteQuestion(id);
	}
}
