package demo.quiz.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.quiz.Feign.QuizInterface;
import demo.quiz.Model.QuestionWrapper;
import demo.quiz.Model.Quiz;
import demo.quiz.Model.QuizDTO;
import demo.quiz.Model.Response;
import demo.quiz.Repository.QuizRepo;

@Service
public class QuizService {
	@Autowired
	QuizInterface quizInterface;
	
	@Autowired
	QuizRepo quizRepo;

	public ResponseEntity<String> createQuiz(QuizDTO quiz) {
		List<Integer> questions=quizInterface.generateQuestions(quiz.getCategory(),quiz.getNumQuestions()).getBody();
		Quiz newQuiz = new Quiz();
		newQuiz.setCategory(quiz.getCategory());
		newQuiz.setNumQ(quiz.getNumQuestions());
		newQuiz.setQuestions(questions);
		quizRepo.save(newQuiz);
		return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
		Quiz quiz=quizRepo.findById(id).get();
		List<Integer> questions = quiz.getQuestions();
		ResponseEntity<List<QuestionWrapper>> questionResponse = quizInterface.getQuestions(questions);
		return questionResponse;
	}

	public ResponseEntity<Integer> submitQuiz(List<Response> answers) {
		Integer score = quizInterface.getScore(answers).getBody();
		return new ResponseEntity<>(score, HttpStatus.OK);
	}
}
