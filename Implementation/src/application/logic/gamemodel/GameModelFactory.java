package application.logic.gamemodel;

import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.GameModel;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.port.IGameModel;
import application.logic.gamemodel.port.IGameModelPort;
import application.logic.gamesettings.IGameSettingsFactory;
import application.logic.gamesettings.port.IGameModelSettings;

public class GameModelFactory implements IGameModelFactory, IGameModelPort, IGameModel {

	private IGameModel game;
	private IGameModelSettings settings = IGameSettingsFactory.FACTORY.getGameSettingsPort().getGameModelSettings();
	
	private void mkGameModel() {
		if(this.game == null) {
			this.game = new GameModel(settings);
		}
	}
	
	@Override
	public void reset() {
		this.mkGameModel();
		this.game.reset();
	}
	
	// =========== Implementation IGameModelFactory ===========
	@Override
	public IGameModelPort getGameModelPort() {
		return this;
	}

	// =========== Implementation IGameModelPort ===========
	@Override
	public IGameModel getGameModel() {
		return this;
	}
	
	// =========== Implementation IGameModel ===========
	@Override
	public Player[] getPlayers() {
		this.mkGameModel();
		return this.game.getPlayers();
	}

	@Override
	public Matchfield getMatchfield() {
		this.mkGameModel();
		return this.game.getMatchfield();
	}

	@Override
	public AField[] getFigurePositionsOfPlayer(Player player) {
		this.mkGameModel();
		return this.game.getFigurePositionsOfPlayer(player);
	}

	@Override
	public int getMatchfieldSize() {
		this.mkGameModel();
		return this.game.getMatchfieldSize();
	}

	@Override
	public boolean allFiguresInMatchfield(Player player) {
		this.mkGameModel();
		return this.game.allFiguresInMatchfield(player);
	}

	@Override
	public Collision moveFigure(int steps, Figure figure) {
		this.mkGameModel();
		return this.game.moveFigure(steps, figure);
	}

	@Override
	public Collision addFigureForPlayer(Player player) {
		this.mkGameModel();
		return this.game.addFigureForPlayer(player);
	}

}
