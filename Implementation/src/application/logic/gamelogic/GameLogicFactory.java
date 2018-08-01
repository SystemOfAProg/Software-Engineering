package application.logic.gamelogic;

import application.gui.port.IObserver;
import application.logic.dice.port.IDice;
import application.logic.gamelogic.impl.GamePlay;
import application.logic.gamelogic.impl.GamePlayData;
import application.logic.gamelogic.impl.GameStart;
import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamelogic.port.IGamePort;
import application.logic.gamelogic.port.IGameStart;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.gamesettings.port.IGameQuestionSettings;
import application.logic.questionmanager.impl.Question;
import application.logic.questionmanager.impl.QuestionCategory;
import application.logic.stateMachine.IStateMachineFactory;
import application.logic.stateMachine.port.IState;
import application.logic.stateMachine.port.IStateMachine;
import application.logic.stateMachine.port.IStateMachinePort;
import application.logic.stateMachine.port.ISubject;

public class GameLogicFactory implements
		IGameLogicFactory, IGamePort, IGamePlay, IGameStart {

	private IGamePlay gamePlay;
	private IGameStart gameStart;
	private IStateMachineFactory stateMachineFactory = IStateMachineFactory.FACTORY;

	public GameLogicFactory() {

	}

	@Override
	public void start() {
		this.mkGameStart();
		this.gameStart.start();
	}

	private void mkGameStart() {
		if (this.gameStart == null) {
			this.gameStart = new GameStart();
		}
	}

	private void mkGamePlay() {
		if (this.gamePlay == null) {
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

	@Override
	public void handleShowTutorial(boolean controllerInput) {
		this.mkGameStart();
		this.gameStart.handleShowTutorial(controllerInput);
	}

	@Override
	public void handleUseStandardSet(boolean controllerInput) {
		this.mkGameStart();
		this.gameStart.handleUseStandardSet(controllerInput);
	}

	@Override
	public void handlePlayerCount(int controllerInput) {
		this.mkGameStart();
		this.gameStart.handlePlayerCount(controllerInput);
	}

	@Override
	public void handleFieldsPerPlayer(int controllerInput) {
		this.mkGameStart();
		this.gameStart.handleFieldsPerPlayer(controllerInput);
	}

	@Override
	public void handleFiguresPerPlayer(int controllerInput) {
		this.mkGameStart();
		this.gameStart.handleFiguresPerPlayer(controllerInput);
	}

	@Override
	public void handleKnowledgeIndicatorSteps(int controllerInput) {
		this.mkGameStart();
		this.gameStart.handleKnowledgeIndicatorSteps(controllerInput);
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

	@Override
	public void handleChooseRepeat(boolean controllerInput) {
		this.mkGamePlay();
		this.gamePlay.handleChooseRepeat(controllerInput);
	}

	@Override
	public Player getCurrentPlayer() {
		this.mkGamePlay();
		return this.gamePlay.getCurrentPlayer();
	}

	@Override
	public void handleDiceThrown() {
		this.mkGamePlay();
		this.gamePlay.handleDiceThrown();
	}

	@Override
	public GamePlayData getGameData() {
		this.mkGamePlay();
		return this.gamePlay.getGameData();
	}

	@Override
	public IDice getGameDice() {
		this.mkGamePlay();
		return this.gamePlay.getGameDice();
	}

	@Override
	public void reset() {
		this.mkGamePlay();
		this.gamePlay.reset();
	}

	// ========== Implementation of ISubject ==========

	@Override
	public void attach(IObserver obs) {
		this.stateMachineFactory.getStateMachinePort().getStateMachine().attach(obs);
	}

	@Override
	public void detach(IObserver obs) {
		// TODO: detach Observers from Logics State Machine
	}

	@Override
	public void notifyObservers() {
		// TODO call Logics State Machines notifyObservers
	}

}
