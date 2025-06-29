package demo.quiz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.quiz.Model.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {

}
