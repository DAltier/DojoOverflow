package com.codingdojo.danaaltier.dojoOverflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingdojo.danaaltier.dojoOverflow.models.Answer;
import com.codingdojo.danaaltier.dojoOverflow.repostories.AnswerRepo;

@Service
public class AnswerService {
	
	// Adding the Answer repository as dependency
	private final AnswerRepo answerRepo;
	
	
	// Constructor
	public AnswerService(AnswerRepo answerRepo) {
		this.answerRepo = answerRepo;
	}
	
	
	// Methods
	// Create new answer
	public Answer createAns(Answer answer) {
		return answerRepo.save(answer);
	}
	
	// Return all answers
	public List<Answer> getAll() {
		return answerRepo.findAll();
	}
}
