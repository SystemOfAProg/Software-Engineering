package application.logic.gamemodel.implementation.questions;

import application.logic.gamemodel.implementation.player.*;
import application.logic.gamemodel.implementation.matchfield.*;
import application.logic.gamemodel.implementation.questions.*;
import application.logic.gamemodel.interfaces.*;
import application.logic.questionmanager.serialization.QuestionReader;

public class Question {

	private QuestionCategory category;
	private String question;
	private Answer[] answers;
	private int correctAnswerIndex;

	public Question(String question, QuestionCategory category, Answer[] answers, int correctAnswerIndex) {
		this.question = question;
		this.category = category;
		this.answers = answers;
		this.correctAnswerIndex = correctAnswerIndex;
	}
	
	public boolean isAnswerCorrect(int index) {
		return index == this.correctAnswerIndex;
	}
	
	public String getQuestionSentence() {
		return this.question;
	}
	
	public Answer[] getAnswers() {
		return this.answers;
	}
	
	public QuestionCategory getCategory() {
		return this.category;
	}
	
	public Answer getCorrectAnswer() {
		return this.answers[correctAnswerIndex];
	}
	
}
