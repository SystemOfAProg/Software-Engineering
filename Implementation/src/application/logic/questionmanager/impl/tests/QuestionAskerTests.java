package application.logic.questionmanager.impl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import application.logic.gamemodel.impl.GameModel;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.impl.questions.Answer;
import application.logic.gamemodel.impl.questions.KnowledgeIndicator;
import application.logic.gamemodel.impl.questions.Question;
import application.logic.gamemodel.impl.questions.QuestionCategory;
import application.logic.questionmanager.impl.QuestionAsker;

public class QuestionAskerTests {

	public int playerCount = 4;
	public int fieldsPerPlayer = 10;
	public int figuresPerPlayer = 3;
	public int knowledgeIndicatorSize = 5;
	
	@Test
	public void getQuestions() {
		// standardField
		GameModel game = new GameModel(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
		QuestionAsker asker = new QuestionAsker(game);
		QuestionCategory[] categories = game.getQuestionCategories();
		for (int i=0; i<categories.length; i++) {
			for(int j=0; j<10; j++) {
				// Check if Questions are available -> no Exceptions
				Question newQuestion = asker.getNextQuestion(categories[i]);
				// Check if right category
				assertEquals(newQuestion.getCategory().getName(), categories[i].getName());
				// Check if question not empty
				assert(newQuestion.getQuestionSentence().length() != 0);
			}
		}
	}
	
	/**
	 * Test if answering the question correctly is handled correctly
	 */
	@Test
	public void testAnswering() {
		// standardField
		GameModel game = new GameModel(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
		QuestionAsker asker = new QuestionAsker(game);
		QuestionCategory[] categories = game.getQuestionCategories();
		for (int i=0; i<categories.length; i++) {
			for(int j=0; j<10; j++) {
				Question newQuestion = asker.getNextQuestion(categories[i]);
				Answer correct = newQuestion.getCorrectAnswer();
				assert(correct.getAnswerSentence().trim().length() > 0);
				assertTrue(newQuestion.isAnswerCorrect(correct));
				int wrongAnswerIndex = -1;
				if( newQuestion.getCorrectAnswerIndex() < newQuestion.getAnswers().length ) {
					wrongAnswerIndex = newQuestion.getCorrectAnswerIndex() + 1;
				} else if (newQuestion.getCorrectAnswerIndex()>0) {
					wrongAnswerIndex = newQuestion.getCorrectAnswerIndex() - 1;
				}
				if(wrongAnswerIndex != -1) {
					assertFalse(newQuestion.isAnswerCorrect(wrongAnswerIndex));
				}
			}
		}
	}
	
	/**
	 * Check if all Question Categories are always returned in the right order
	 */
	@Test
	public void testGetCategoriesOrder() {
		GameModel game = new GameModel(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
		QuestionCategory[] categoriesTemplate = game.getQuestionCategories();
		QuestionCategory[] categories;
		for(int i=0; i<50; i++) {
			categories = game.getQuestionCategories();
			for(int j=0; j<categories.length; j++) {
				// Check if Array always has the same order
				assertEquals(categoriesTemplate[j].getName(), categories[j].getName());				
			}
		}
	}
	
	/**
	 * Check if all Knowledge Indicators are initialized properly
	 */
	@Test
	public void testKnowledgeIndicators() {
		GameModel game = new GameModel(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
		Player[] players = game.getPlayers();
		for(Player player: players) {
			for(KnowledgeIndicator i: player.getKnowledgeIndicators()) {
				QuestionCategory expected = i.getCategory();
				QuestionCategory actual = null;
				for(QuestionCategory c: game.getQuestionCategories()) {
					if(i.getCategory().equals(c)) {
						actual = c;
					}
				}
				assertTrue(expected.equals(actual));
			}
		}
	}
	
}
