package application.logic.gamesettings;

import application.logic.gamesettings.port.IGameSettingsPort;

public interface IGameSettingsFactory {

	IGameSettingsFactory FACTORY = new GameSettingsFactory();
	
	IGameSettingsPort getGameSettingsPort();
	
}
