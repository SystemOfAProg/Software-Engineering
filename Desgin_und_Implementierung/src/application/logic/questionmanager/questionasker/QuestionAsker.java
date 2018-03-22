package application.logic.questionmanager.questionasker;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import application.logic.gamemodel.implementation.Game;
import application.logic.gamemodel.implementation.questions.Question;
import application.logic.gamemodel.implementation.questions.QuestionCategory;

public class QuestionAsker {

	private Game game;
	private HashMap<QuestionCategory, LinkedList<Question>> toAsk;
	private HashMap<QuestionCategory, LinkedList<Question>> alreadyAsked;
	
	public QuestionAsker(Game game) {
		this.game = game;
		this.alreadyAsked = new HashMap<QuestionCategory, LinkedList<Question>>();
		this.toAsk = new HashMap<QuestionCategory, LinkedList<Question>>();
		Question[] questions = this.shuffleQuestions(this.game.getQuestions());
		for(Question question: questions) {
			LinkedList<Question> alreadyAdded = (this.toAsk.get(question) == null)? new LinkedList<>():this.toAsk.get(question);
			alreadyAdded.add(question);
			this.toAsk.put(question.getCategory(), alreadyAdded);
		}
	}

	// Fisher-Yates Shuffle
	private Question[] shuffleQuestions(Question[] ar) {
		// If running on Java 6 or older, use `new Random()` on RHS here
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			Question a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
		return ar;
	}
	
	public Question getNextQuestion(QuestionCategory category) {
		if(this.toAsk.get(category).isEmpty()) {
			Question[] alreadyAsked = (Question[])this.alreadyAsked.get(category).toArray();
			alreadyAsked = this.shuffleQuestions(alreadyAsked);
			for(Question question: alreadyAsked) {
				LinkedList<Question> alreadyAdded = this.toAsk.get(question);
				alreadyAdded.add(question);
				this.toAsk.put(question.getCategory(), alreadyAdded);
			}
		}
		Question next = this.toAsk.get(category).getFirst();
		LinkedList<Question> questionsToAskOfCategory = (this.toAsk.get(category) == null)? new LinkedList<>() : this.toAsk.get(category);
		LinkedList<Question> questionsAlreadyAskedOfCategory = (this.alreadyAsked.get(category) == null)? new LinkedList<>() : this.alreadyAsked.get(category);
		questionsToAskOfCategory.remove(next);
		questionsAlreadyAskedOfCategory.add(next);
		this.toAsk.put(category, questionsToAskOfCategory);
		this.alreadyAsked.put(category, questionsAlreadyAskedOfCategory);
		return next;
	}
	
}
