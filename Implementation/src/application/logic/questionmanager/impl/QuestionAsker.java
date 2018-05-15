package application.logic.questionmanager.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import application.logic.gamemodel.impl.GameModel;
import application.logic.gamemodel.impl.questions.Question;
import application.logic.gamemodel.impl.questions.QuestionCategory;

public class QuestionAsker {

	private GameModel game;
	private HashMap<QuestionCategory, LinkedList<Question>> toAsk;
	private HashMap<QuestionCategory, LinkedList<Question>> alreadyAsked;
	
	public QuestionAsker(GameModel game) {
		this.game = game;
		this.alreadyAsked = new HashMap<QuestionCategory, LinkedList<Question>>();
		this.toAsk = new HashMap<QuestionCategory, LinkedList<Question>>();
		LinkedList<Question> questions = new LinkedList<>(Arrays.asList(this.game.getQuestions()));
		questions = this.shuffleQuestions(questions);
		for(Question question: questions) {
			LinkedList<Question> alreadyAdded = (this.toAsk.get(question) == null)? new LinkedList<>():this.toAsk.get(question);
			alreadyAdded.add(question);
			this.toAsk.put(question.getCategory(), alreadyAdded);
		}
	}

	// Fisher-Yates Shuffle
	private LinkedList<Question> shuffleQuestions(LinkedList<Question> ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.size() - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			Question a = ar.get(index);
			ar.set(index, ar.get(i));
			ar.set(i, a);
		}
		return ar;
	}
	
	public Question getNextQuestion(QuestionCategory category) {
		if(this.toAsk.get(category).isEmpty()) {
			LinkedList<Question> alreadyAsked = this.alreadyAsked.get(category);
			alreadyAsked = this.shuffleQuestions(alreadyAsked);
			for(Question question: alreadyAsked) {
				LinkedList<Question> alreadyAdded = (this.toAsk.get(question)!=null)? this.toAsk.get(question): new LinkedList<Question>();
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
