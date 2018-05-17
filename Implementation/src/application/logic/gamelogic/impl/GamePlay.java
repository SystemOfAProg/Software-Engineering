package application.logic.gamelogic.impl;

import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.GameModel;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.questionmanager.impl.Question;
import application.logic.questionmanager.impl.QuestionCategory;

// Utility for delegating requests for Game-Play Functions
// to Game Facade
public class GamePlay implements IGamePlay{

	private GameModel game;
	
	// You can use the Game-Facade Instance from Game Start
	public GamePlay(IGameModelSettings gameModelSettings) {
		this.game = new GameModel(gameModelSettings);
	}

	@Override
	public Player[] getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matchfield getMatchfield() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question[] getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionCategory[] getQuestionCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AField[] getFigurePositionsOfPlayer(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMatchfieldSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collision moveFigure(int steps, Figure figure) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collision addFigureForPlayer(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean allFiguresInMatchfield(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

}
