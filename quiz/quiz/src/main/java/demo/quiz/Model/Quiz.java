package demo.quiz.Model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String category;
	private int numQ;
	
	@ElementCollection
	private List<Integer> questions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNumQ() {
		return numQ;
	}

	public void setNumQ(int numQ) {
		this.numQ = numQ;
	}

	public List<Integer> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Integer> questions) {
		this.questions = questions;
	}
}
