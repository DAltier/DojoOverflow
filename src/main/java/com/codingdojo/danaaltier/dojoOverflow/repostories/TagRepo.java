package com.codingdojo.danaaltier.dojoOverflow.repostories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.danaaltier.dojoOverflow.models.Tag;

@Repository
public interface TagRepo extends CrudRepository<Tag, Long> {

}