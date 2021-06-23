package com.codingdojo.danaaltier.dojoOverflow.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.codingdojo.danaaltier.dojoOverflow.models.Tag;
import com.codingdojo.danaaltier.dojoOverflow.repostories.TagRepo;

@Service
public class TagService {
	
	// Adding the Tag repository as dependency
	private final TagRepo tagRepo;
	
	
	// Constructor
	public TagService(TagRepo tagRepo) {
		this.tagRepo = tagRepo;
	}
	
	
	// Methods
	// Create new tag
	public Tag createTag(String tag) {
		Tag myTag = new Tag();
		myTag.setSubject(tag);
		return tagRepo.save(myTag);
		
	}
	
	
	// Return all tags
	public ArrayList<Tag> findAllTags() {
		return (ArrayList<Tag>) tagRepo.findAll();
	}
}
