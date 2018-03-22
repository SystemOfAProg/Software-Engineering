package application.logic.questionmanager.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import application.logic.gamemodel.implementation.Game;
import application.logic.gamemodel.implementation.questions.Question;
import application.logic.gamemodel.implementation.questions.QuestionCategory;
import application.logic.questionmanager.questionasker.QuestionAsker;

public class QuestionAskerTests {

	public int playerCount = 4;
	public int fieldsPerPlayer = 10;
	public int figuresPerPlayer = 3;
	
	@Test
	public void test() {
		// standardField
		Game game = new Game(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer);
		QuestionAsker asker = new QuestionAsker(game);
		QuestionCategory category = game.getQuestionCategories()[0];
		for(QuestionCategory c: game.getQuestionCategories()) {
			System.out.println(c.getName());			
		}
		System.out.println(asker.getNextQuestion(category).getQuestionSentence());
		System.out.println(asker.getNextQuestion(category).getQuestionSentence());
		System.out.println(asker.getNextQuestion(category).getQuestionSentence());
	}
	
}
