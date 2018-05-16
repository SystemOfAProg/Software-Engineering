package application.logic.questionmanager.impl;

public class Answer {

	private String answerSentence;
	
	public Answer(String answerSentence) {
		this.answerSentence = answerSentence;
	}
	
	public String getAnswerSentence() {
		return this.answerSentence;
	}
	
	public boolean equals(Answer answer) {
		return this.answerSentence.trim().equals(answer);
	}
	
}
