package application.logic.gamemodel.impl.player;

import java.util.ArrayList;
import java.util.Arrays;

import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.GameModel;
import application.logic.gamemodel.impl.matchfield.HomeField;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.matchfield.StartingField;

public class Player {

	private GameModel game;
	private Figure[] figures;
	private StartingField start;
	private HomeField[] homes;
	
	private int figuresCount = 3;
	
	private String name;
	private int playerCounter;
	
	public Player(GameModel game, int count, Matchfield field, int figuresPerPlayer) {
		this.game = game;
		this.name = "Player "+ Integer.toString(count);
		this.playerCounter = count;
		this.figuresCount = figuresPerPlayer;
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
	
	public Figure[] getFiguresInField() {
		int figuresInFieldCount = Arrays.stream(this.figures).filter(figure -> figure.isInField()).toArray().length;
		Figure[] figuresInField = new Figure[figuresInFieldCount];
		int j = 0;
		for(int i=0; i<this.figures.length; i++) {
			if(this.figures[i].isInField()) {
				figuresInField[j] = this.figures[i];
				j++;
			}
		}
		return figuresInField;
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
