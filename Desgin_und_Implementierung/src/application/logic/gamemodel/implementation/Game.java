package application.logic.gamemodel.implementation;

import application.logic.gamemodel.implementation.player.*;
import application.logic.gamemodel.implementation.matchfield.*;
import application.logic.gamemodel.implementation.questions.*;
import application.logic.gamemodel.interfaces.*;
import application.logic.questionmanager.serialization.QuestionReader;

public class Game {

	private Player[] players;
	private Matchfield field;
	private Question[] questions;
	// File-Location where the questions, answers and categories are stored
	private String questionFileLocation = "./resources/questions.json";
	
	// ~~~~~~~~~~~~~~~~~ Create new Game ~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a new game with all its neccessary components
	 * @param gameFieldSizeFactor 	How many Fields per Players:
	 * 								4 Players, Factor 10 -> 40 Fields
	 * @param playerCount 			Count of Players
	 */
	public Game(int gameFieldSizeFactor, int playerCount) {
		if (gameFieldSizeFactor > 0 && playerCount > 0){
			int gameFieldSize = gameFieldSizeFactor * playerCount;
			System.out.println("> Create new Game...");
			createMatchfield(gameFieldSize, playerCount);
			createPlayers(playerCount);
			createQuestions();
			System.out.println("> Game successfully created!");
		} else {
			throw new IllegalArgumentException("Please insert valid playercount and fieldSizeFactor!");
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
		System.out.println("> Created " + this.players.length + " players");
	}
	
	private void createQuestions() {
		QuestionReader qr = new QuestionReader(this.questionFileLocation);
		this.questions = qr.getQuestion();
		qr.printQuestionCountAndLocation();
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
		AField[] positions = new AField[player.getFigures().length];
		for(int i=0; i<player.getFigures().length; i++) {
			positions[i] = player.getFigures()[i].getCurrentLocation();
		}
		return positions;
	}

	public Collision movePlayer(int steps, Figure figure, Player player) {
		AField oldField = figure.getCurrentLocation();
		AField newField = this.field.calculateNewField(oldField, steps);
		return figure.move(newField);
	}

	public Collision addFigureForPlayer(Player player) {
		for(Figure figure: player.getFigures()) {
			if(!figure.isInField()) {
				return figure.move(player.getStartingField());
			}
		}
		return new Collision();
	}
	
	public boolean allFiguresInMatchfield(Player player) {
		for(Figure figure: player.getFigures()) {
			if(!figure.isInField()) {
				return false;
			}
		}
		return true;
	}
	
	// ~~~~~~~~~~~~~~~~~ Utils ~~~~~~~~~~~~~~~~~~
	
	public void printField() {
		for(AField field: this.field.getAllFields()) {
			if(StartingField.class.isInstance(field)) {
				Figure occupant = field.getFigure();
				String fieldViewContent = (occupant == null)? " ": Integer.toString(occupant.getPlayer().getPlayerCount());
				System.out.println("> ( " + fieldViewContent + " ) " + field.getFieldIdentifier() );				
			} else if (StandardField.class.isInstance(field)){
				Figure occupant = field.getFigure();
				String fieldViewContent = (occupant == null)? " ": Integer.toString(occupant.getPlayer().getPlayerCount());
				System.out.println("> ( "+ fieldViewContent +" ) " + field.getFieldIdentifier());
			}
		}		
	}
	
	public void printCurrentState() {
		// TODO: print summary of the current status of the game
		for(Player player: this.players) {
			System.out.println("Informationen zu " + player.getPlayerName());
			System.out.println(" | --- Figuren:");
			for(Figure figure: player.getFigures()) {
				System.out.println(" |     |--- " + figure.getFigureIdentifier());
				System.out.println(" |     |         | --- Location: " + figure.getCurrentLocation().getFieldIdentifier());
			}
		}
	}
	
}
