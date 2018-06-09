package application.logic.gamelogic.port;

import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.gamesettings.port.IGameQuestionSettings;

/**
 * Interface for Game Logic to start a new game.
 * Main Focus of this interface is to collect Data from the user
 * about settings 
 */
public interface IGameStart {

	// Getter
	IGameModelSettings getGameModelSettings();
	IGameQuestionSettings getGameQuestionSettings();
	// Start the Game
	void start();
	// Handler for inputs given to the controller by player
	void handleShowTutorial(boolean controllerInput);
	void handleUseStandardSet(boolean controllerInput);
	void handlePlayerCount(int controllerInput);
	void handleFieldsPerPlayer(int controllerInput);
	void handleFiguresPerPlayer(int controllerInput);
	void handleKnowledgeIndicatorSteps(int controllerInput);

}
