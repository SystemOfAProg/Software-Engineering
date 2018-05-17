package application.logic.questionmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import application.logic.gamemodel.IGameModelFactory;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.port.IGameModel;
import application.logic.questionmanager.impl.Answer;
import application.logic.questionmanager.impl.KnowledgeIndicator;
import application.logic.questionmanager.impl.KnowledgeIndicatorManager;
import application.logic.questionmanager.impl.Question;
import application.logic.questionmanager.impl.QuestionAsker;
import application.logic.questionmanager.impl.QuestionCategory;
import application.logic.questionmanager.impl.QuestionReader;

public class QuestionAskerImplTests {

	public int playerCount = 4;
	public int fieldsPerPlayer = 10;
	public int figuresPerPlayer = 3;
	public int knowledgeIndicatorSize = 5;
	public String testFileLocation = "";
	
	@Test
	public void testGetQuestions() {
		QuestionAsker asker = new QuestionAsker();
		QuestionReader qr = new QuestionReader(this.testFileLocation);
		QuestionCategory[] categories = asker.getQuestionCategories();
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
		QuestionAsker asker = new QuestionAsker();
		QuestionCategory[] categories = asker.getQuestionCategories();
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
		QuestionAsker asker = new QuestionAsker();
		QuestionCategory[] categoriesTemplate = asker.getQuestionCategories();
		QuestionCategory[] categories;
		for(int i=0; i<50; i++) {
			categories = asker.getQuestionCategories();
			for(int j=0; j<categories.length; j++) {
				// Check if Array always has the same order
				assertEquals(categoriesTemplate[j].getName(), categories[j].getName());				
			}
		}
	}
	
	/**
	 * Check if all Knowledge Indicators are initialized properly and have the right categories
	 */
	@Test
	public void testKnowledgeIndicators() {
		IGameModel game = IGameModelFactory.FACTORY.getGameModelPort().getGameModel();
		QuestionAsker asker = new QuestionAsker();
		KnowledgeIndicatorManager kiManager = new KnowledgeIndicatorManager(
				game.getPlayers(),
				asker.getQuestionCategories(),
				fieldsPerPlayer,
				fieldsPerPlayer
		);
		Player[] players = game.getPlayers();
		for(Player player: players) {
			for(KnowledgeIndicator i: kiManager.getKnowledgeIndicators(player)) {
				QuestionCategory expected = i.getCategory();
				QuestionCategory actual = null;
				for(QuestionCategory c: asker.getQuestionCategories()) {
					if(i.getCategory().equals(c)) {
						actual = c;
					}
				}
				assertTrue(expected.equals(actual));
			}
		}
	}
	
}
