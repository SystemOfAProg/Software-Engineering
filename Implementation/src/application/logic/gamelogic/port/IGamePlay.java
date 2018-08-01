package application.logic.gamelogic.port;

import application.logic.dice.port.IDice;
import application.logic.gamelogic.impl.GamePlayData;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Player;
import application.logic.questionmanager.impl.Question;
import application.logic.questionmanager.impl.QuestionCategory;

public interface IGamePlay {

	// Read State
	Player[] getPlayers();

	Matchfield getMatchfield();

	Question[] getQuestions();

	QuestionCategory[] getQuestionCategories();

	AField[] getFigurePositionsOfPlayer(Player player);

	int getMatchfieldSize();

	// Manipulate State
	boolean allFiguresInMatchfield(Player player);

	void reset();

	// Handle Requests from Controller
	void handleAdjustIndicators();

	void handleMoveFigure();

	void handleCheckAnswer(int controllerInput);

	void handleChooseNextQuestion();

	void handleChooseCategory(int controllerInput);

	void handleAddFigureToMatchfield();

	void handleChooseFigureInField(int controllerInput);

	void handleGetNextPlayer();

	void handleThrowDice();

	void handleChooseRepeat(boolean controllerInput);

	Player getCurrentPlayer();

	void handleDiceThrown();

	GamePlayData getGameData();

	IDice getGameDice();
}
