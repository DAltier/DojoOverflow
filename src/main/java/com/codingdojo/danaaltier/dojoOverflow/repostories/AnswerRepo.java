package com.codingdojo.danaaltier.dojoOverflow.repostories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.danaaltier.dojoOverflow.models.Answer;

@Repository
public interface AnswerRepo extends CrudRepository<Answer, Long> {
	
	List<Answer> findAll();
	
}
