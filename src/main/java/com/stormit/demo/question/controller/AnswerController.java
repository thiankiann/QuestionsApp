package com.stormit.demo.question.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.stormit.demo.question.domain.model.Answer;
import com.stormit.demo.question.domain.model.Question;
import com.stormit.demo.question.service.AnswerService;
import com.stormit.demo.question.service.QuestionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/questions/{question-id}/answers")
public class AnswerController {

	private final AnswerService answerService;

	public AnswerController(AnswerService answerService) {
		this.answerService = answerService;
	}

	@GetMapping
	List<Answer> getAnswers(@PathVariable("question-id") UUID questionId){
		return answerService.getAnswers(questionId);
	}

	@GetMapping("{answer-id}")
	Answer getAnswer(@PathVariable("question-id") UUID questionId, @PathVariable("answer-id") UUID answerId){
		return answerService.getAnswer(answerId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Answer createAnswer(@PathVariable("question-id") UUID questionId, @RequestBody Answer answer){
		return answerService.createAnswer(questionId, answer);
	}

	@PutMapping("{answer-id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	Answer updateAnswer(@PathVariable("question-id") UUID questionId, @PathVariable("answer-id") UUID answerId,
	                    @RequestBody Answer answer){
		return answerService.updateAnswer(answerId, answer);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{answer-id}")
	void deleteAnswer(@PathVariable("answer-id") UUID answerId){
		answerService.deleteAnswer(answerId);
	}
}
