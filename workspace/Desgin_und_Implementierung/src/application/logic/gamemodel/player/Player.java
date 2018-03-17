package application.logic.gamemodel.player;

import application.logic.gamemodel.Game;
import application.logic.gamemodel.matchfield.*;
import application.logic.gamemodel.questions.*;

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
		this.start = field.setStartFiledForPlayer(this);
	}
	
	private void createHomeFields() {
		this.homes = new HomeField[this.figuresCount];
		for(int i=0; i<this.figuresCount; i++) {
			this.homes[i] = new HomeField(this);
		}
	}
	
	private void createFigures() {
		this.figures = new Figure[this.figuresCount];
		for(int i=0; i<this.figuresCount; i++) {
			this.figures[i] = new Figure(this);
		}
	}
	
	public int getPlayerCount() {
		return this.playerCounter;
	}
	
	public String getPlayerName() {
		return this.name;
	}
	
}
