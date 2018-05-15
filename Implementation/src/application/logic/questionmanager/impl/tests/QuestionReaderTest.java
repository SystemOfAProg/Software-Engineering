package application.logic.questionmanager.impl.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.logic.gamemodel.impl.questions.Answer;
import application.logic.gamemodel.impl.questions.Question;
import application.logic.questionmanager.impl.QuestionReader;

public class QuestionReaderTest {

	/**
	 * Test compares actual content and array-lengths of testQuestion-JSON
	 * Any changes in its content and length have to be adjusted here!
	 */
	
	private String testFileLocation   = "./resources/questionsTest.json";
	private String questionSignature  = "Testfrage ";
	private String answerSignature    = "Antwort ";
	private String categorySignature  = "Kategorie ";
	private String correctSignature   = "(korrekt)";
	
	@Test
	public void testReadTestQuestionFileAndCheckContent() {
		
		QuestionReader qr = new QuestionReader(testFileLocation);
		Question[] questions = qr.getQuestion();
		// Check amount of questions
		assertEquals(4, questions.length);
		
		for(int i=0; i<questions.length; i++) {
			
			Question question = questions[i];
			String expectedQuestion = questionSignature + (i+1);
			String actualQuestion = question.getQuestionSentence();
			// Check Question-Content
			assertEquals(expectedQuestion,actualQuestion);
			// Check Answer-Count
			assertEquals(4, questions[i].getAnswers().length);
			
			for (int j=0; j<questions[i].getAnswers().length; j++){
				
				Answer answer = question.getAnswers()[j];
				String expectedAnwer = answerSignature + (i+1) + "." + (j+1);
				String actualAnswer = answer.getAnswerSentence().replace((" " + correctSignature), "");
				// Check Answer-Content
				assertEquals(expectedAnwer, actualAnswer);
			}
			
			String expectedCategory = categorySignature + (i+1);
			String actualCategory = question.getCategory().getName();
			// Check Category
			assertEquals(expectedCategory, actualCategory);
			// Check Correct Answer
			assert(question.getCorrectAnswer().getAnswerSentence().contains(correctSignature));
		}
	}
	
}
