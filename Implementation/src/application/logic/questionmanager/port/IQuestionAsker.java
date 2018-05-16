package application.logic.questionmanager.port;

import application.logic.questionmanager.impl.Answer;
import application.logic.questionmanager.impl.Question;
import application.logic.questionmanager.impl.QuestionCategory;

public interface IQuestionAsker {

	/**
	 * Get a question to a given Category, that has not already been asked.
	 * If all questions of a category have already been asked, they get shuffeled
	 * and asekd again.
	 * @param category Category of the question
	 * @return Question Question that gets asked
	 */
	Question getNextQuestion(QuestionCategory category);
	
	/**
	 * Get all questions no matter what category they belong to.
	 * @return Questions of all categories as single Array
	 */
	Question[] getAllQuestions();
	
	/**
	 * Get all existing Question Categories
	 * @return Question Categories as Array
	 */
	QuestionCategory[] getQuestionCategories();
	
	/**
	 * Checks if an given answer is the correct answer to a question.
	 * @param answer Answer given to the question
	 * @param question Question that is answered
	 * @return true if the given answer is correct
	 */
	boolean answerQuestion(Answer answer, Question question);
	
	/**
	 * Checks if the index referencing an answer inside the given question-object
	 * is the correct answer to a question.
	 * @param answerIndex index of the answer that the quesiton object holds
	 * @param question Question that is answered
	 * @return true if the given index is correct
	 */
	boolean answerQuestion(int answerIndex, Question question);
	
}
