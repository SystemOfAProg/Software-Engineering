package application.logic.gamemodel.impl.questions;

public class QuestionCategory {

	private String name;
	
	public QuestionCategory(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean equals(QuestionCategory category) {
		if(category == null) {
			return false;
		}
		return (this.getName().trim() == category.getName().trim()); 
	}
	
}
