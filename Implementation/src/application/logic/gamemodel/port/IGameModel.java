package application.logic.gamemodel.port;

import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.impl.questions.Question;
import application.logic.gamemodel.impl.questions.QuestionCategory;

public interface IGameModel {

	// ~~~~~~~~~~~~~~~~~ Read State ~~~~~~~~~~~~~~~~~~

	/**
	 * Returns all players included in the current game.
	 * @return All Players as Array
	 */
	Player[] 			getPlayers();
	
	/**
	 * Returns the current matchfield.
	 * @return Matchfield
	 */
	Matchfield 			getMatchfield();
	
	/**
	 * Returns all Questions as one Array from all categories.
	 * @return Question Objects as Array
	 */
	Question[] 			getQuestions();
	
	/**
	 * Returns all existing question categories.
	 * @return Question Categories as Array
	 */
	QuestionCategory[] 	getQuestionCategories();
	
	/**
	 * Looks up where all figures of a specific players figures are located and 
	 * return their position.
	 * @param player Player whose figures position should be searched
	 * @return Fields the figures are placed on as array
	 */
	AField[] 			getFigurePositionsOfPlayer(Player player);
	
	/**
	 * 
	 * @return
	 */
	int 				getMatchfieldSize();
	boolean 			allFiguresInMatchfield(Player player);
	// ~~~~~~~~~~~~~~~~~ Write State ~~~~~~~~~~~~~~~~~~
	Collision 			moveFigure(int steps, Figure figure);
	Collision 			addFigureForPlayer(Player player);
	void 				reset();
}
