package demo.quiz.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import demo.quiz.Feign.QuizInterface;
import demo.quiz.Model.QuestionWrapper;
import demo.quiz.Model.Quiz;
import demo.quiz.Model.QuizDTO;
import demo.quiz.Model.Response;
import demo.quiz.Repository.QuizRepo;
import demo.quiz.Service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuestionWrapper questionWrapper;
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quiz) {
		return quizService.createQuiz(quiz);
	}
	
	@PostMapping("getQuiz/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
		return quizService.getQuiz(id);
	}
	
	@PostMapping("submitQuiz")
	public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> answers) {
		return quizService.submitQuiz(answers);
	}
}
