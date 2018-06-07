package application.logic.gamelogic.impl;

import application.logic.questionmanager.impl.Question;

public class GamePlayData {

	public int currentPlayerIndex;
	public int currentFigureIndex;
	public int currentCategoryIndex;
	public int diceRollCounter;
	public Question currentQuestion;

	public GamePlayData() {
	}

	public void reset() {
		this.currentPlayerIndex = 0;
		this.currentFigureIndex = 0;
		this.currentCategoryIndex = 0;
		this.diceRollCounter = 0;
	}

}