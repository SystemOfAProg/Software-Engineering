package application.logic.gamelogic.impl;

public class GamePlayData {
	
	public int currentPlayerIndex;
	public int currentFigureIndex;
	public int diceRollCounter;

	public GamePlayData() {}
	
	public void reset() {
		this.currentPlayerIndex = 0;
		this.currentFigureIndex = 0;
		this.diceRollCounter = 0;
	}
}