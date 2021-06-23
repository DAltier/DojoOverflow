package com.codingdojo.danaaltier.dojoOverflow.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.danaaltier.dojoOverflow.models.Answer;
import com.codingdojo.danaaltier.dojoOverflow.models.Question;
import com.codingdojo.danaaltier.dojoOverflow.models.Tag;
import com.codingdojo.danaaltier.dojoOverflow.services.AnswerService;
import com.codingdojo.danaaltier.dojoOverflow.services.QuestionService;
import com.codingdojo.danaaltier.dojoOverflow.services.TagService;

@Controller
public class MainController {

	// Adding the Answer, Question, and Tag services as dependencies
	private final QuestionService questionService;
	private final TagService tagService;
	private final AnswerService answerService;
	
	
	// Constructor
	public MainController(QuestionService questionService, TagService tagService, AnswerService answerService ) {
		this.questionService=questionService;
		this.tagService=tagService;
		this.answerService=answerService;
	}
	
	
	// Index Route
	@RequestMapping("/")
	public String index() {
		return "index.jsp";
	}
	
	
	// Dashboard route
	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		List<Question> myQuestionList = questionService.findAll();
		model.addAttribute("questions", myQuestionList);
		return "dashboard.jsp";
	}
	
	
	// GET route for adding a new question
	@RequestMapping("/questions/new")
	public String newQuestion(@ModelAttribute("addQuestion") Question question) {
		return "newQuestion.jsp";
	}
	
	
	// POST route for adding a new question
	@PostMapping("/questions/new")
	public String createQuestion(@Valid @ModelAttribute("addQuestion") Question question, BindingResult result, @RequestParam("myTag") String myTag, RedirectAttributes flash) {
		
		// Validate the number of tags
		int numComma = myTag.replaceAll("[^,]", "").length();
		if (numComma>2) {
			flash.addFlashAttribute("errors", "You can add 3 tags at the most.");
		}
		
		// Validate tags have no upper case characters	
		if (!myTag.equals(myTag.toLowerCase())) {
			flash.addFlashAttribute("errors", "must be all lower case");
		}

		if (flash.getFlashAttributes().size()>0) {
			return "redirect:/questions/new";
		} else if(result.hasErrors()){	
			// Validate questions - cannot be empty
			return "newQuestion.jsp";
		} else {
			// Create a new question
			Question myQ = questionService.createQuestion(question);
			
			// Split each tag from a string input and add to array list
			List<String> items = (List<String>)Arrays.asList(myTag.trim().split("\\s*,\\s*"));
			
			// Create tags array list
			ArrayList<Tag> tags = new ArrayList<Tag>();
			for(int i=0;i<items.size();i++) {
				tags.add(tagService.createTag(items.get(i)));
			}
			
			// Set tags to question
			myQ.setTags(tags);
			
			// Update question
			questionService.createQuestion(myQ);
			
			return "redirect:/dashboard";
		}
	}
	
	
	// GET route for displaying question
	@RequestMapping("/questions/{id}")
	public String show(@ModelAttribute("ans") Answer answer, @PathVariable("id") Long id, Model model) {
		Question myQuestion = questionService.findQ(id);
		model.addAttribute("myquestion", myQuestion);
		model.addAttribute("myTags", myQuestion.getTags());
		model.addAttribute("answers", myQuestion.getAnswers());
		return "showQuestion.jsp";
	}
	
	
	// POST route for adding a new answer
	@PostMapping("/questions")
	public String addAnswer(@Valid @ModelAttribute("ans") Answer ans, @RequestParam("question") Long id, BindingResult result, RedirectAttributes flash) {
		if (ans.getAnswer().length() < 3) {
			flash.addFlashAttribute("errors", "Answer must be at least 3 characters long.");
		}
		
		if (flash.getFlashAttributes().size()>0) {
			return "redirect:/questions/"+id;
		} else {
			answerService.createAns(ans);
			Long myID = ans.getQuestion().getId();
			return "redirect:/questions/"+myID;
		}
	}
}
