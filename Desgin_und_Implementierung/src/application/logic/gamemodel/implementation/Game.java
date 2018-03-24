package application.logic.gamemodel.implementation;

import application.logic.gamemodel.implementation.player.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import application.logic.gamemodel.implementation.matchfield.*;
import application.logic.gamemodel.implementation.questions.*;
import application.logic.gamemodel.interfaces.*;
import application.logic.questionmanager.serialization.QuestionReader;

public class Game {

	private Player[] players;
	private Matchfield field;
	private Question[] questions;
	private QuestionCategory[] questionCategories;
	// File-Location where the questions, answers and categories are stored
	private String questionFileLocation = "./resources/questions.json";
	
	// ~~~~~~~~~~~~~~~~~ Create new Game ~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a new game with all its neccessary components
	 * @param gameFieldSizeFactor 	How many Fields per Players:
	 * 								4 Players, Factor 10 -> 40 Fields
	 * @param playerCount 			Count of Players
	 * @param figureForPlayer		How many figures should be added for each player
	 */
	public Game(int gameFieldSizeFactor, int playerCount, int figuresPerPlayer, int knowledgeIndicatorSize) {
		if (gameFieldSizeFactor > 0 && playerCount > 0 && figuresPerPlayer > 0 && knowledgeIndicatorSize > 0){
			int gameFieldSize = gameFieldSizeFactor * playerCount;
			createMatchfield(gameFieldSize, playerCount);
			createQuestions();
			setQuestionCategories();
			createPlayers(playerCount, figuresPerPlayer, knowledgeIndicatorSize);
		} else {
			throw new IllegalArgumentException("Please insert valid playercount and fieldSizeFactor!");
		}
	}
	
	
	private void createMatchfield(int gameFieldSize, int playerCount) {
		this.field = new Matchfield(gameFieldSize, playerCount, this);
	}
	
	private void createPlayers(int size, int figuresPerPlayer, int knowledgeIndicatorSize) {
		players = new Player[size];
		for(int i=0; i<size; i++) {
			players[i] = new Player(this, i, this.field, figuresPerPlayer, knowledgeIndicatorSize, this.questionCategories);
		}
	}
	
	private void createQuestions() {
		QuestionReader qr = new QuestionReader(this.questionFileLocation);
		this.questions = qr.getQuestion();
	}
	
	private void setQuestionCategories() {
		ArrayList<QuestionCategory> categories = new ArrayList<QuestionCategory>();
		for(Question question: this.questions) {
			QuestionCategory category = question.getCategory();
			if(!categories.contains(category)) {
				categories.add(category);
			}
		}
		this.questionCategories = categories.toArray(new QuestionCategory[categories.size()]);
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
	
	public QuestionCategory[] getQuestionCategories() {
		return this.questionCategories;
	}

	// ~~~~~~~~~~~~~~~~~ Data Manipulation ~~~~~~~~~~~~~~~~~~

	public AField[] getFigurePositionsOfPlayer(Player player) {
		AField[] positions = new AField[player.getFigures().length];
		for(int i=0; i<player.getFigures().length; i++) {
			positions[i] = player.getFigures()[i].getCurrentLocation();
		}
		return positions;
	}

	public int getMatchfieldSize() {
		return this.field.getSize();
	}
	
	public Collision moveFigure(int steps, Figure figure) {
		Player player = figure.getPlayer();
		AField oldField = figure.getCurrentLocation();
		AField newField = this.field.calculateNewField(oldField, steps);
		return figure.move(newField);
	}

	public Collision addFigureForPlayer(Player player) throws IllegalAccessError {
		for(Figure figure: player.getFigures()) {
			if(!figure.isInField()) {
				return figure.move(player.getStartingField());
			}
		}
		throw new IllegalAccessError("All figures for player" + player.getPlayerName() + " are already added to the matchfield!");
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
			String fieldViewContent = "";
			if(!(field.getFigures().isEmpty())) {
				for(Figure occupant: field.getFigures()) {
					fieldViewContent += Integer.toString(occupant.getPlayer().getPlayerCount());
				}
			} else {
				fieldViewContent = " ";
			}
			if(StartingField.class.isInstance(field)) {
				System.out.println("> ( " + fieldViewContent + " ) " + field.getFieldIdentifier() );				
			} else if (StandardField.class.isInstance(field)){
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
