package application.logic.questionmanager.impl;

import application.logic.gamemodel.impl.player.Player;

public class KnowledgeIndicator {

	private Player player;
	private QuestionCategory category;
	private int position;
	private int min;
	private int max;
	
	public KnowledgeIndicator(int min, int max, QuestionCategory category, Player player) {
		this.min = min;
		this.max = max;
		this.position = this.min;
		this.category = category;
		this.player = player;
	}
	
	public int increment() {
		if(this.position < this.max) {
			this.position++;			
		}
		return this.position;
	}
	
	public int decrement() {
		if(this.position > this.min) {
			this.position--;			
		}
		return this.position;
	}
	
	public boolean isAtMax() {
		return this.position == this.max;
	}
	
	public QuestionCategory getCategory() {
		return this.category;
	}
	
}
