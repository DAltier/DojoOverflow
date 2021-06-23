package com.codingdojo.danaaltier.dojoOverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.danaaltier.dojoOverflow.models.Question;
import com.codingdojo.danaaltier.dojoOverflow.repostories.QuestionRepo;

@Service
public class QuestionService {
	
	// Adding the Question repository as dependency
	private final QuestionRepo questionRepo;
	
	
	// Constructor
	public QuestionService(QuestionRepo questionRepo) {
		this.questionRepo = questionRepo;
	}
	
	
	// Methods
	// Create new question
	public Question createQuestion(Question question) {
		return questionRepo.save(question);
	}
	
	
	// Find question by Id
	public Question findQ(Long id) {
		Optional<Question> myQ = questionRepo.findById(id);
		if (myQ.isPresent()) {
			return myQ.get();
		}else {
			return null;
		}
	}
	
	
	// Return all questions
	public List<Question> findAll(){
		return questionRepo.findAll();
	}
}
