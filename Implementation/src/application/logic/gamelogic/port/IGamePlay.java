package application.logic.gamelogic.port;

import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.impl.questions.Question;
import application.logic.gamemodel.impl.questions.QuestionCategory;

public interface IGamePlay {
	
	// Read State
	Player[] getPlayers();
	Matchfield getMatchfield();
	Question[] getQuestions();
	QuestionCategory[] getQuestionCategories();
	AField[] getFigurePositionsOfPlayer(Player player);
	int getMatchfieldSize();
	// Manipulate State
	Collision moveFigure(int steps, Figure figure);
	Collision addFigureForPlayer(Player player);
	boolean allFiguresInMatchfield(Player player);
	
}
