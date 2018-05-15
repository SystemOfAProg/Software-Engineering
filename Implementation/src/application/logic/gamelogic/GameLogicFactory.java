package application.logic.gamelogic;

import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamelogic.port.IGamePort;
import application.logic.gamelogic.port.IGameStart;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.Game;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.impl.questions.Question;
import application.logic.gamemodel.impl.questions.QuestionCategory;

public class GameLogicFactory implements IGameLogicFactory, IGamePort, IGamePlay, IGameStart {

	public GameLogicFactory() {
		
	}
	
	private IGamePlay gamePlay;
	private IGameStart gameStart;

	// ========== Create Ports and Interfaces ==========
	
	@Override
	public IGamePort gamePort() {
		return this;
	};

	@Override
	public IGamePlay gamePlay() {
		return this;
	}

	@Override
	public IGameStart gameStart() {
		return this;
	}

	// ========== Implementation of GameStart ==========
	
	@Override
	public Game createNewGame(int gameFieldSizeFactor, int playerCount, int figuresPerPlayer,
			int knowledgeIndicatorSize) {
		// TODO Auto-generated method stub
		return null;
	}

	// ========== Implementation of GamePlay ==========
	
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
