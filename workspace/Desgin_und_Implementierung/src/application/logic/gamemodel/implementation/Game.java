package application.logic.gamemodel.implementation;

import application.logic.gamemodel.implementation.player.*;
import application.logic.gamemodel.implementation.matchfield.*;
import application.logic.gamemodel.implementation.questions.*;
import application.logic.gamemodel.interfaces.*;
import application.logic.questionmanager.QuestionReader;

public class Game {

	private Player[] players;
	private Matchfield field;
	private Question[] questions;
	// File-Location where the questions, answers and categories are stored
	private String questionFileLocation = "./resources/questions.json";
	
	// ~~~~~~~~~~~~~~~~~ Create new Game ~~~~~~~~~~~~~~~~~~
	
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
		players = new Player[size];
		for(int i=0; i<size; i++) {
			players[i] = new Player(this, i, this.field);
		}
	}
	
	private void createQuestions() {
		QuestionReader qr = new QuestionReader(this.questionFileLocation);
		this.questions = qr.getQuestion();
		qr.printQuestions();
	}
	
	// ~~~~~~~~~~~~~~~~~ Getter ~~~~~~~~~~~~~~~~~~
	
	public Player[] getPlayers() {
		return this.players;
	}
	
	public Matchfield getMatchfield() {
		return this.field;
	}
	
	public Question[] getQuestions() {
		return this.questions;
	}
	

	// ~~~~~~~~~~~~~~~~~ Data Manipulation ~~~~~~~~~~~~~~~~~~

	public AField[] getFigurePositionsOfPlayer(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collision movePlayer(int steps, Figure figure, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addFigureForPlayer(Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
