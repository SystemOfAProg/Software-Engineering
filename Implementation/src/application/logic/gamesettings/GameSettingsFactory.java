package application.logic.gamesettings;

import application.logic.gamesettings.impl.GameModelSettings;
import application.logic.gamesettings.impl.GameQuestionSettings;
import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.gamesettings.port.IGameQuestionSettings;
import application.logic.gamesettings.port.IGameSettingsPort;

public class GameSettingsFactory implements IGameSettingsFactory, IGameSettingsPort, IGameModelSettings, IGameQuestionSettings {

	private IGameModelSettings gameModelSettings;
	private IGameQuestionSettings gameQuestionSettings;
	
	private void mkGameModelSettings() {
		if(this.gameModelSettings == null) {
			this.gameModelSettings = new GameModelSettings();
		}
	}
	
	private void mkGameQuestionSettings() {
		if(this.gameQuestionSettings == null) {
			this.gameQuestionSettings = new GameQuestionSettings();
		}
	}
	
	// =============== IGameQuestionSettings =============== 
	
	@Override
	public void setKnowledgeIndicatorSize(int size) {
		this.mkGameQuestionSettings();
		this.gameQuestionSettings.setKnowledgeIndicatorSize(size);
	}

	@Override
	public int getKnowledgeIndicatorSize() {
		this.mkGameQuestionSettings();
		return this.gameQuestionSettings.getKnowledgeIndicatorSize();
	}

	// =============== IGameModelSettings =============== 
	
	@Override
	public void setFieldsPerPlayer(int fieldsPerPlayer) {
		this.mkGameModelSettings();
		this.gameModelSettings.setFieldsPerPlayer(fieldsPerPlayer);
		
	}

	@Override
	public int getFieldsPerPlayer() {
		this.mkGameModelSettings();
		return this.gameModelSettings.getFieldsPerPlayer();
	}

	@Override
	public void setPlayerCount(int playerCount) {
		this.mkGameModelSettings();
		this.gameModelSettings.setPlayerCount(playerCount);
	}

	@Override
	public int getPlayerCount() {
		this.mkGameModelSettings();
		return this.gameModelSettings.getPlayerCount();
	}

	@Override
	public void setFiguresPerPlayer(int figuresPerPlayer) {
		this.mkGameModelSettings();
		this.gameModelSettings.setFiguresPerPlayer(figuresPerPlayer);
	}

	@Override
	public int getFiguresPerPlayer() {
		this.mkGameModelSettings();
		return this.gameModelSettings.getFiguresPerPlayer();
	}

	// =============== IGameSettingsPort =============== 
	
	@Override
	public IGameModelSettings getGameModelSettings() {
		return this;
	}

	@Override
	public IGameQuestionSettings getGameQuestionSettings() {
		return this;
	}

	// =============== IGameSettingsFactory =============== 
	
	@Override
	public IGameSettingsPort getGameSettingsPort() {
		return this;
	}

	@Override
	public void resetQuestionSettings() {
		this.mkGameQuestionSettings();
		this.gameQuestionSettings.resetQuestionSettings();
	}

	@Override
	public void resetModelSettings() {
		this.mkGameModelSettings();
		this.gameModelSettings.resetModelSettings();
	}

}
