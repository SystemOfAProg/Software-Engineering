package application.logic.gamemodel;

import application.logic.gamemodel.player.*;
import application.logic.gamemodel.matchfield.*;
import application.logic.gamemodel.questions.*;

public class Game {

	private Player[] players;
	private Matchfield field;
	private Question[] questions;
	
	public Game(int gameFieldSize, int playerCount) {
		if (gameFieldSize > 0 && playerCount > 0){
			createMatchfield(gameFieldSize, playerCount);
			createPlayers(playerCount);
			createQuestions();
		} else {
			
		}
	}
	
	private void createMatchfield(int gameFieldSize, int playerCount) {
		this.field = new Matchfield(gameFieldSize, playerCount, this);
	}
	
	private void createPlayers(int size) {
		for(int i=0; i<size; i++) {
			players[i] = new Player(this, i, this.field);
		}
	}
	
	private void createQuestions() {
		// TODO: specify what todo here
		this.questions = null;
	}
	
}
