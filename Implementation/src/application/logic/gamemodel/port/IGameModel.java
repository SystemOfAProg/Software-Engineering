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
	Player[] 			getPlayers();
	Matchfield 			getMatchfield();
	Question[] 			getQuestions();
	QuestionCategory[] 	getQuestionCategories();
	AField[] 			getFigurePositionsOfPlayer(Player player);
	int 				getMatchfieldSize();
	boolean 			allFiguresInMatchfield(Player player);
	// ~~~~~~~~~~~~~~~~~ Write State ~~~~~~~~~~~~~~~~~~
	Collision 			moveFigure(int steps, Figure figure);
	Collision 			addFigureForPlayer(Player player);
}
