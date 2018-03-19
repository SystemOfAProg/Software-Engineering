package application.logic.gamemodel.implementation.player;

import application.logic.gamemodel.implementation.player.*;
import application.logic.gamemodel.implementation.Game;
import application.logic.gamemodel.implementation.matchfield.*;
import application.logic.gamemodel.implementation.questions.*;
import application.logic.gamemodel.interfaces.*;
import application.logic.questionmanager.QuestionReader;

public class Player {

	private Game game;
	private Figure[] figures;
	private StartingField start;
	private HomeField[] homes;
	private KnowledgeIndicator[] indicators;
	
	private int figuresCount = 3;
	
	private String name;
	private int playerCounter;
	
	public Player(Game game, int count, Matchfield field) {
		this.game = game;
		this.name = "Player "+ Integer.toString(count);
		this.playerCounter = count;
		setStartField(field);
		createHomeFields();
		createFigures();
	}
	
	private void setStartField(Matchfield field) {
		this.start = field.setStartFieldForPlayer(this);
	}
	
	private void createHomeFields() {
		this.homes = new HomeField[this.figuresCount];
		for(HomeField hf: homes) {
			hf = new HomeField(this);
		}
	}
	
	private void createFigures() {
		this.figures = new Figure[this.figuresCount];
		for(Figure f: this.figures) {
			f = new Figure(this);
		}
	}
	
	public int getPlayerCount() {
		return this.playerCounter;
	}
	
	public String getPlayerName() {
		return this.name;
	}
	
}
