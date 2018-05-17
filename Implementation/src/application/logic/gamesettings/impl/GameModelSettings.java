
package application.logic.gamesettings.impl;

import application.logic.gamesettings.port.IGameModelSettings;

public class GameModelSettings implements IGameModelSettings {
	
	private int fieldsPerPlayer = this.DEFAULT_FIELDSPERPLAYER;
	private int playerCount = this.DEFAULT_PLAYERCOUNT;
	private int figuresPerPlayer = this.DEFAULT_FIGURESPERPLAYER;
	
	@Override
	public void setFieldsPerPlayer(int fieldsPerPlayer) {
		this.fieldsPerPlayer = fieldsPerPlayer;
	}

	@Override
	public int getFieldsPerPlayer() {
		return this.fieldsPerPlayer;
	}

	@Override
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	@Override
	public int getPlayerCount() {
		return this.playerCount;
	}

	@Override
	public void setFiguresPerPlayer(int figuresPerPlayer) {
		this.figuresPerPlayer = figuresPerPlayer;
	}

	@Override
	public int getFiguresPerPlayer() {
		return this.figuresPerPlayer;
	}

	@Override
	public void resetModelSettings() {
		this.fieldsPerPlayer = this.DEFAULT_FIELDSPERPLAYER;
		this.playerCount = this.DEFAULT_PLAYERCOUNT;
		this.figuresPerPlayer = this.DEFAULT_FIGURESPERPLAYER;
	}

}
