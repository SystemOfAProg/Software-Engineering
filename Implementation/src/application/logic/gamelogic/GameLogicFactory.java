package application.logic.gamelogic;

import application.logic.gamelogic.impl.GamePlay;
import application.logic.gamelogic.impl.GameStart;
import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamelogic.port.IGamePort;
import application.logic.gamelogic.port.IGameStart;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.gamesettings.port.IGameQuestionSettings;
import application.logic.questionmanager.impl.Question;
import application.logic.questionmanager.impl.QuestionCategory;

public class GameLogicFactory implements IGameLogicFactory, IGamePort, IGamePlay, IGameStart {

	private IGamePlay gamePlay;
	private IGameStart gameStart;
	
	public GameLogicFactory() {}
	
	private void mkGameStart() {
		if(this.gameStart == null) {
			this.gameStart = new GameStart();
		}
	}
	
	private void mkGamePlay() {
		if(this.gamePlay == null) {
			this.gamePlay = new GamePlay(this.gameStart.getGameModelSettings());
		}
	}

	// ========== Create Ports and Interfaces ==========
	
	@Override
	public IGamePort getGamePort() {
		return this;
	};

	@Override
	public IGamePlay getGamePlay() {
		return this;
	}

	@Override
	public IGameStart getGameStart() {
		return this;
	}

	// ========== Implementation of GameStart ==========
	
	@Override
	public IGameModelSettings getGameModelSettings() {
		this.mkGameStart();
		return this.gameStart.getGameModelSettings();
	}

	@Override
	public IGameQuestionSettings getGameQuestionSettings() {
		this.mkGameStart();
		return this.gameStart.getGameQuestionSettings();
	}

	// ========== Implementation of GamePlay ==========
	
	@Override
	public Player[] getPlayers() {
		this.mkGamePlay();
		return this.gamePlay.getPlayers();
	}

	@Override
	public Matchfield getMatchfield() {
		this.mkGamePlay();
		return this.gamePlay.getMatchfield();
	}

	@Override
	public Question[] getQuestions() {
		this.mkGamePlay();
		return this.gamePlay.getQuestions();
	}

	@Override
	public QuestionCategory[] getQuestionCategories() {
		this.mkGamePlay();
		return this.gamePlay.getQuestionCategories();
	}

	@Override
	public AField[] getFigurePositionsOfPlayer(Player player) {
		this.mkGamePlay();
		return this.getFigurePositionsOfPlayer(player);
	}

	@Override
	public int getMatchfieldSize() {
		this.mkGamePlay();
		return this.getMatchfieldSize();
	}

	@Override
	public Collision moveFigure(int steps, Figure figure) {
		this.mkGamePlay();
		return this.gamePlay.moveFigure(steps, figure);
	}

	@Override
	public Collision addFigureForPlayer(Player player) {
		this.mkGamePlay();
		return this.addFigureForPlayer(player);
	}

	@Override
	public boolean allFiguresInMatchfield(Player player) {
		this.mkGamePlay();
		return this.gamePlay.allFiguresInMatchfield(player);
	}

	@Override
	public void handleAdjustIndicators() {
		this.mkGamePlay();
		this.gamePlay.handleAdjustIndicators();
	}

	@Override
	public void handleMoveFigure() {
		this.mkGamePlay();
		this.gamePlay.handleMoveFigure();
	}

	@Override
	public void handleCheckAnswer(int controllerInput) {
		this.mkGamePlay();
		this.gamePlay.handleCheckAnswer(controllerInput);
	}

	@Override
	public void handleChooseNextQuestion() {
		this.mkGamePlay();
		this.gamePlay.handleChooseNextQuestion();
	}

	@Override
	public void handleChooseCategory(int controllerInput) {
		this.mkGamePlay();
		this.gamePlay.handleChooseCategory(controllerInput);
	}

	@Override
	public void handleAddFigureToMatchfield() {
		this.mkGamePlay();
		this.gamePlay.handleAddFigureToMatchfield();
	}

	@Override
	public void handleChooseFigureInField(int controllerInput) {
		this.mkGamePlay();
		this.gamePlay.handleChooseFigureInField(controllerInput);
	}

	@Override
	public void handleGetNextPlayer() {
		this.mkGamePlay();
		this.gamePlay.handleGetNextPlayer();
	}

	@Override
	public void handleThrowDice() {
		this.mkGamePlay();
		this.gamePlay.handleThrowDice();
	}
	
}
