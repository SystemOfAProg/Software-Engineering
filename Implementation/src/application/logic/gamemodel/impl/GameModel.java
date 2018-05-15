package application.logic.gamemodel.impl;

import java.util.ArrayList;

import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.impl.questions.Question;
import application.logic.gamemodel.impl.questions.QuestionCategory;
import application.logic.gamemodel.port.IGameModel;
import application.logic.questionmanager.impl.QuestionReader;

public class GameModel implements IGameModel{

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
	public GameModel(int gameFieldSizeFactor, int playerCount, int figuresPerPlayer, int knowledgeIndicatorSize) {
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
	
	public boolean allFiguresInMatchfield(Player player) {
		for(Figure figure: player.getFigures()) {
			if(!figure.isInField()) {
				return false;
			}
		}
		return true;
	}	

	// ~~~~~~~~~~~~~~~~~ Data Manipulation ~~~~~~~~~~~~~~~~~~
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
	
}
