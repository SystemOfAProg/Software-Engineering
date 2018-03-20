package application.logic.gamemodel.implementation.player;

import application.logic.gamemodel.implementation.player.*;
import application.logic.gamemodel.implementation.Game;
import application.logic.gamemodel.implementation.matchfield.*;
import application.logic.gamemodel.implementation.questions.*;
import application.logic.gamemodel.interfaces.*;
import application.logic.questionmanager.serialization.QuestionReader;

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
		for(int i=0; i<this.homes.length; i++){
			this.homes[i] = new HomeField(this, i);
		}
	}
	
	private void createFigures() {
		this.figures = new Figure[this.figuresCount];
		for(int i=0; i<this.figuresCount; i++) {
			this.figures[i] = new Figure(this, this.homes[i], i);
			if(this.figures[i].move(homes[i]).collisionHappened()) {
				// When starting there are no collisions allowed
				throw new IllegalStateException();
			}
		}
	}
	
	public int getPlayerCount() {
		return this.playerCounter;
	}
	
	public String getPlayerName() {
		return this.name;
	}
	
	public Figure[] getFigures() {
		return this.figures;
	}
	
	/**
	 * Check if both Players reference the same player.
	 * @param player Player to check
	 * @return true if both player-counts are identical
	 */
	public boolean equals(Player player) {
		if(player != null) {
			return this.getPlayerCount() == player.getPlayerCount();			
		} else {
			return false;
		}
	}

	public AField getStartingField() {
		return this.start;
	}
	
}
