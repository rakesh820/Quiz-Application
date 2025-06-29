package demo.question_service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.question_service.Model.Question;
import demo.question_service.Model.QuestionWrapper;
import demo.question_service.Model.Response;
import demo.question_service.Repository.QuestionRepo;

@Service
public class QuestionService {

	@Autowired
	QuestionRepo questionRepo;
	
	public ResponseEntity<List<Question>> getallQuestions() {
		try {
			if (questionRepo.findAll().isEmpty()) {
				return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);
	}

	public ResponseEntity<List<Question>> getQuestionsbyCategory(String category) {
		try {
			Optional<List<Question>> questions = questionRepo.findByCategory(category);
			if (!questions.isPresent()) {
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(questions.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> addQuestion(Question question) {
		try {
			if (question.getCategory() == null || question.getDifficulty_level() == null || 
				question.getQuestionTitle() == null || question.getOption1() == null || 
				question.getOption2() == null || question.getOption3() == null || 
				question.getOption4() == null || question.getAnswer() == null) {
				return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
			}
			questionRepo.save(question);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	@Transactional
	public ResponseEntity<String> deleteQuestion(Question question) {
		try {
			if (question.getQuestionTitle() == null) {
				return new ResponseEntity<>("Invalid Input. Please provide Question Title", HttpStatus.BAD_REQUEST);
			}
			if (questionRepo.findByQuestionTitle(question.getQuestionTitle()).isEmpty()) {
				return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
			}
			questionRepo.deleteByQuestionTitle(question.getQuestionTitle());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Question Deleted Successfully", HttpStatus.OK);
	}

	public ResponseEntity<String> updateQuestion(Question question) {
		try {
			if (question.getCategory() == null || question.getDifficulty_level() == null || 
					question.getQuestionTitle() == null || question.getOption1() == null || 
					question.getOption2() == null || question.getOption3() == null || 
					question.getOption4() == null || question.getAnswer() == null) {
					return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
				}
			questionRepo.save(question);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Question Updated Successfully", HttpStatus.OK);
	}

	public ResponseEntity<List<Integer>> generateQuestions(String category, int numQ) {
		PageRequest pageRequest = PageRequest.of(0, numQ);
		Optional<List<Integer>> questions=questionRepo.findRandomQuestionsfromCategory(category, pageRequest);
		if (!questions.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(questions.get(), HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> questionsIds) {
		List<Question> questions = new ArrayList<>();
		List<QuestionWrapper> questionWrappers = new ArrayList<>();
		for(Integer id:questionsIds) {
			Optional<Question> q= questionRepo.findByid(id);
			if(q.isPresent()) {
				questions.add(q.get());
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		for(Question q:questions) {
			QuestionWrapper questionWrapper = new QuestionWrapper();
			questionWrapper.setCategory(q.getCategory());
			questionWrapper.setDifficulty(q.getDifficulty_level());
			questionWrapper.setQuestion(q.getQuestionTitle());
			questionWrapper.setOption1(q.getOption1());
			questionWrapper.setOption2(q.getOption2());
			questionWrapper.setOption3(q.getOption3());
			questionWrapper.setOption4(q.getOption4());
			questionWrappers.add(questionWrapper);
		}
		return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int score = 0;
		for(Response response: responses) {
			Optional<Question> question=questionRepo.findByid(response.getId());
			if(!question.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			if(response.getAnswer().equals(question.get().getAnswer())) {
				score++;
			}
		}
		return new ResponseEntity<>(score, HttpStatus.OK);
	}

}
