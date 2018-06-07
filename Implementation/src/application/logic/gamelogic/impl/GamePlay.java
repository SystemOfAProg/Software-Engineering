package application.logic.gamelogic.impl;

import application.logic.dice.IDiceFactory;
import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamemodel.IGameModelFactory;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.questionmanager.impl.Question;
import application.logic.questionmanager.impl.QuestionCategory;
import application.logic.stateMachine.IStateMachineFactory;
import application.logic.stateMachine.port.IState.State;

// Utility for delegating requests for Game-Play Functions
// to Game Facade
public class GamePlay implements IGamePlay{

	private IGameModelFactory game = IGameModelFactory.FACTORY;
	private IDiceFactory dice = IDiceFactory.FACTORY;
	private IStateMachineFactory stateMachine = IStateMachineFactory.FACTORY;
	
	private GamePlayData data = new GamePlayData();
	private GamePlayUtils util = new GamePlayUtils(this.game, this.dice, this.data);
	
	public GamePlay(IGameModelSettings gameModelSettings) {
		this.game.getGameModelPort().getGameModel().setSettingsAndReset(gameModelSettings);
		this.data.reset();
	}
	
	// ==================== Reaction to explicit states ====================

	@Override
	public void handleAdjustIndicators() {
		// TODO implement
		
	}

	@Override
	public void handleMoveFigure() {
		// TODO implement
		
	}

	@Override
	public void handleCheckAnswer(int controllerInput) {
		// TODO implement
		
	}

	@Override
	public void handleChooseNextQuestion() {
		// TODO implement
		
	}

	@Override
	public void handleChooseCategory(int controllerInput) {
		// TODO implement
		
	}

	@Override
	public void handleAddFigureToMatchfield() {
		Collision collision = this.game.getGameModelPort().getGameModel().addFigureForPlayer(this.util.getCurrentPlayer());
		if(collision.collisionHappened()) {
			this.stateMachine.getStateMachinePort().getStateMachine().setState(State.chooseCategory);
		}
	}

	@Override
	public void handleChooseFigureInField(int controllerInput) {
		
	}

	@Override
	public void handleGetNextPlayer() {
		this.util.IncrCurrentPlayerIndex();
		this.data.diceRollCounter = 0;
		this.stateMachine.getStateMachinePort().getStateMachine().setState(State.throwDice);
	}
	
	@Override
	public void handleThrowDice() {
		int result = this.dice.getDicePort().getDice().roll();
		this.data.diceRollCounter++;
		if(util.figureAddingAllowed(result)) {
			if(!util.figureCanBeAdded()) {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.chooseFigureInField);
			} else if(util.figureCanBeAdded()) {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.addFigureToMatchField);
			}
		} else {
			if(util.currentPlayerHasFigureInField()) {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.chooseFigureInField);			
			} else if(!util.currentPlayerHasFigureInField() && this.data.diceRollCounter < 3) {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.throwDice);
			} else {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.getNextPlayer);
			}
		}
	}

	// ==================== Read State of Game for GUI ====================
	
	@Override
	public Player[] getPlayers() {
		return this.game.getGameModelPort().getGameModel().getPlayers();
	}

	@Override
	public Matchfield getMatchfield() {
		return this.game.getGameModelPort().getGameModel().getMatchfield();
	}

	@Override
	public Question[] getQuestions() {
		// TODO implement
		return null;
	}

	@Override
	public QuestionCategory[] getQuestionCategories() {
		// TODO implement
		return null;
	}

	@Override
	public AField[] getFigurePositionsOfPlayer(Player player) {
		return this.game.getGameModelPort().getGameModel().getFigurePositionsOfPlayer(player);
	}

	@Override
	public int getMatchfieldSize() {
		// TODO implement
		return 0;
	}

	@Override
	public Collision moveFigure(int steps, Figure figure) {
		// TODO implement
		return null;
	}

	@Override
	public Collision addFigureForPlayer(Player player) {
		// TODO implement
		return null;
	}

	@Override
	public boolean allFiguresInMatchfield(Player player) {
		// TODO implement
		return false;
	}

}
