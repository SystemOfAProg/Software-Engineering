package application.logic.gamelogic.port;

import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.gamesettings.port.IGameQuestionSettings;

/**
 * Interface for Game Logic to start a new game.
 * Main Focus of this interface is to collect Data from the user
 * about settings 
 */
public interface IGameStart {

	IGameModelSettings getGameModelSettings();
	IGameQuestionSettings getGameQuestionSettings();
	
}
