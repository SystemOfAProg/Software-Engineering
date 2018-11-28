package application.logic.gamemodel.impl;

import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.matchfield.StartingField;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.port.IGameModel;
import application.logic.gamesettings.port.IGameModelSettings;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements IGameModel{

	private Player[] players;
	private Matchfield field;
	
	private IGameModelSettings gameModelSettings;
	
	private int gameFieldSizeFactor;
	private int playerCount;
	private int figuresPerPlayer;
	
	// ~~~~~~~~~~~~~~~~~ Create new Game ~~~~~~~~~~~~~~~~~~
	/**
	 * Creates a new game with all its neccessary components
	 * @param settings Setting for the game
	 */
	public GameModel(IGameModelSettings settings) {
		this.gameFieldSizeFactor = settings.getFieldsPerPlayer();
		this.playerCount = settings.getPlayerCount();
		this.figuresPerPlayer = settings.getFiguresPerPlayer();
		this.reset();
	}
	
	
	private void createMatchfield(int gameFieldSize, int playerCount) {
		this.field = new Matchfield(gameFieldSize, playerCount, this);
	}
	
	private void createPlayers(int size, int figuresPerPlayer) {
		players = new Player[size];
		for(int i=0; i<size; i++) {
			players[i] = new Player(this, i, this.field, figuresPerPlayer);
		}
	}
	
	// ~~~~~~~~~~~~~~~~~ Reading current State of Game ~~~~~~~~~~~~~~~~~~
	
	@Override
	public Player[] getPlayers() {
		return this.players;
	}
	
	@Override
	public Matchfield getMatchfield() {
		return this.field;
	}

	@Override
	public AField[] getFigurePositionsOfPlayer(Player player) {
		AField[] positions = new AField[player.getFigures().length];
		for(int i=0; i<player.getFigures().length; i++) {
			positions[i] = player.getFigures()[i].getCurrentLocation();
		}
		return positions;
	}

	@Override
	public int getMatchfieldSize() {
		return this.field.getSize();
	}
	
	@Override
	public boolean allFiguresInMatchfield(Player player) {
		for(Figure figure: player.getFigures()) {
			if(!figure.isInField()) {
				return false;
			}
		}
		return true;
	}	

	// ~~~~~~~~~~~~~~~~~ Changing current State of Game ~~~~~~~~~~~~~~~~~~
	@Override
	public Collision moveFigure(int steps, Figure figure) {
		AField oldField = figure.getCurrentLocation();
		AField newField = this.field.calculateNewField(oldField, steps);
		if (newField instanceof StartingField && ((StartingField) newField).getPlayer().equals(figure.getPlayer())
				&& newField.isOccupied() && newField.getFigures().getFirst().getPlayer().equals(figure.getPlayer())) {
			newField = figure.getPlayer().getHomeFieldOfFigure(figure);
		}
		return figure.move(newField);
	}

	@Override
	public Collision moveFigure(AField destination, Figure figure) {
		return figure.move(destination);
	}

	@Override
	public Collision addFigureForPlayer(Player player) throws IllegalAccessError {
		for(Figure figure: player.getFigures()) {
			if(!figure.isInField()) {
				return figure.move(player.getStartingField());
			}
		}
		throw new IllegalAccessError("All figures for player" + player.getPlayerName() + " are already added to the matchfield!");
	}

	@Override
	public void reset() {
		if (gameFieldSizeFactor > 0 && playerCount > 0 && figuresPerPlayer > 0){
			int gameFieldSize = gameFieldSizeFactor * playerCount;
			createMatchfield(gameFieldSize, playerCount);
			createPlayers(playerCount, figuresPerPlayer);
		} else {
			throw new IllegalArgumentException("Please insert valid playercount and fieldSizeFactor!");
		}
	}


	@Override
	public void setSettingsAndReset(IGameModelSettings settings) {
		this.gameModelSettings = settings;
		this.reset();
	}

}
