package com.codingdojo.danaaltier.dojoOverflow.repostories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.danaaltier.dojoOverflow.models.Question;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Long> {
	
	List<Question> findAll();
	
}
