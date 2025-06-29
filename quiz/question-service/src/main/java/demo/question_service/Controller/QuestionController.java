package demo.question_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.question_service.Service.QuestionService;
import demo.question_service.Model.Question;
import demo.question_service.Model.QuestionWrapper;
import demo.question_service.Model.Response;

import java.util.*;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	QuestionService questionService;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getallQuestions() {
		return questionService.getallQuestions();
	}
	
	@GetMapping("allQuestions/{category}")
	public ResponseEntity<List<Question>> getQuestionsbyCategory(@PathVariable String category) {
		return questionService.getQuestionsbyCategory(category);
	}
	
	@PostMapping("addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@PutMapping("updateQuestion")
	public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
		return questionService.updateQuestion(question);
	}
	
	
	@DeleteMapping("deleteQuestion")
	public ResponseEntity<String> deleteQuestion(@RequestBody Question question) {
		return questionService.deleteQuestion(question);
	}
	
	@PostMapping("generate")
	public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String Category, @RequestParam int numQ) {
		return questionService.generateQuestions(Category, numQ);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionsIds) {
		return questionService.getQuestions(questionsIds);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
		return questionService.getScore(responses);
	}
	
}
