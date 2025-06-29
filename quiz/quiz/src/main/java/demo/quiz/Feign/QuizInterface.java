package demo.quiz.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import demo.quiz.Model.QuestionWrapper;
import demo.quiz.Model.Response;

@FeignClient(name="QUESTION-SERVICE")
public interface QuizInterface {
	
	@PostMapping("question/generate")
	public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String Category, @RequestParam int numQ);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionsIds);
	
	@PostMapping("question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
