package application.logic.questionmanager.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import application.logic.questionmanager.port.IQuestionAsker;

public class QuestionAsker implements IQuestionAsker{

	private HashMap<QuestionCategory, LinkedList<Question>> toAsk;
	private HashMap<QuestionCategory, LinkedList<Question>> alreadyAsked;
	private Question[] questions;
	private QuestionCategory[] questionCategories;
	
	public QuestionAsker() {
		this.alreadyAsked = new HashMap<QuestionCategory, LinkedList<Question>>();
		this.toAsk = new HashMap<QuestionCategory, LinkedList<Question>>();
		QuestionReader qr = new QuestionReader();
		this.questions = qr.getQuestion();
		LinkedList<Question> questionsAsList = new LinkedList<>(Arrays.asList(this.questions));
		LinkedList<QuestionCategory> categories = new LinkedList<>();
		questionsAsList = this.shuffleQuestions(questionsAsList);
		buildQuestionMap(questionsAsList, categories);
		this.questionCategories = categories.toArray(new QuestionCategory[categories.size()]);
	}

	private void buildQuestionMap(LinkedList<Question> questionsAsList, LinkedList<QuestionCategory> categories) {
		for(Question question: questionsAsList) {
			QuestionCategory cat = question.getCategory();
			LinkedList<Question> alreadyAdded = (this.toAsk.get(cat) == null)? new LinkedList<>():this.toAsk.get(cat);
			alreadyAdded.add(question);
			this.toAsk.put(cat, alreadyAdded);
			if(categories.indexOf(cat) != -1) {
				categories.add(cat);
			}
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
	
	@Override
	public Question getNextQuestion(QuestionCategory category) {
		if(this.toAsk.get(category).isEmpty()) {
			LinkedList<Question> alreadyAsked = this.alreadyAsked.get(category);
			alreadyAsked = this.shuffleQuestions(alreadyAsked);
			for(Question question: alreadyAsked) {
				LinkedList<Question> alreadyAdded = (
						this.toAsk.get(question.getCategory())!=null)?
								this.toAsk.get(question.getCategory()): new LinkedList<Question>();
				alreadyAdded.add(question);
				this.toAsk.put(question.getCategory(), alreadyAdded);
			}
		}
		Question next = this.toAsk.get(category).getFirst();
		LinkedList<Question> questionsToAskOfCategory = 
				(this.toAsk.get(category) == null)? new LinkedList<>() : this.toAsk.get(category);
		LinkedList<Question> questionsAlreadyAskedOfCategory = 
				(this.alreadyAsked.get(category) == null)? new LinkedList<>() : this.alreadyAsked.get(category);
		questionsToAskOfCategory.remove(next);
		questionsAlreadyAskedOfCategory.add(next);
		this.toAsk.put(category, questionsToAskOfCategory);
		this.alreadyAsked.put(category, questionsAlreadyAskedOfCategory);
		return next;
	}
	
	@Override
	public Question[] getAllQuestions() {
		return this.questions;
	}

	@Override
	public QuestionCategory[] getQuestionCategories() {
		return this.questionCategories;
	}

	@Override
	public boolean answerQuestion(Answer answer, Question question) {
		return question.getCorrectAnswer().equals(answer);
	}

	@Override
	public boolean answerQuestion(int answerIndex, Question question) {
		return (answerIndex == question.getCorrectAnswerIndex());
	}
	
}
