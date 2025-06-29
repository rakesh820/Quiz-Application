package demo.question_service.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import demo.question_service.Model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, String> {

	Optional<List<Question>> findByCategory(String category);

	Optional<List<Question>> findByQuestionTitle(String question_title);
	
	void deleteByQuestionTitle(String questionTitle);
	
	@Query(value = "SELECT id FROM question WHERE category = ? ORDER BY RAND()", nativeQuery = true)
	Optional<List<Integer>> findRandomQuestionsfromCategory(String category,org.springframework.data.domain.Pageable pageable);
	
	@Query(value="select * from question where id=?",nativeQuery = true)
	Optional<Question> findByid(int id);

}
