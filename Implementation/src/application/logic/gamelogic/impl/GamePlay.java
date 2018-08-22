package application.logic.gamelogic.impl;

import application.logic.dice.IDiceFactory;
import application.logic.dice.port.IDice;
import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamemodel.IGameModelFactory;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamesettings.port.IGameModelSettings;
import application.logic.gamesettings.port.IGameQuestionSettings;
import application.logic.questionmanager.IQuestionManagerFactory;
import application.logic.questionmanager.impl.Question;
import application.logic.questionmanager.impl.QuestionCategory;
import application.logic.stateMachine.IStateMachineFactory;
import application.logic.stateMachine.port.IState;
import application.logic.stateMachine.port.IState.State;

// Utility for delegating requests for Game-Play Functions
// to Game Facade
public class GamePlay implements IGamePlay {

	private IGameModelSettings gameModelSettings;
	private IGameQuestionSettings gameQuestionSettings;
	private IGameModelFactory game = IGameModelFactory.FACTORY;
	private IQuestionManagerFactory questions = IQuestionManagerFactory.FACTORY;
	private IDiceFactory dice = IDiceFactory.FACTORY;
	private IStateMachineFactory stateMachine = IStateMachineFactory.FACTORY;

	private GamePlayData data = new GamePlayData();
	private GamePlayUtils util = new GamePlayUtils(this.game, this.questions, this.dice, this.data);

	public GamePlay(IGameModelSettings gameModelSettings, IGameQuestionSettings gameQuestionSettings) {
		this.gameModelSettings = gameModelSettings;
		this.gameQuestionSettings = gameQuestionSettings;
		this.reset();
	}

	@Override
	public void reset() {
		this.game.getGameModelPort().getGameModel().setSettingsAndReset(this.gameModelSettings);
		this.questions.getQuestionManagerPort().getKnowledgeIndicatorManager().setPlayers(this.getPlayers());
		this.questions.getQuestionManagerPort().getKnowledgeIndicatorManager().setKnowledgeIndicatorSize(0, this.gameQuestionSettings.getKnowledgeIndicatorSize());
		this.data.reset();
	}

	// ==================== Reaction to explicit states ====================

	@Override
	public void handleAnswerCorrect(Figure figureToMove) {
		this.questions.getQuestionManagerPort().getKnowledgeIndicatorManager().increase(util.getCurrentPlayer(),
				util.getCurrentCategory());
		this.game.getGameModelPort().getGameModel().moveFigure(figureToMove.getPlayer().getStartingField(),figureToMove);
		if (util.existsWinner()) {
			// Game is completed, ask if game should be repeated
			this.stateMachine.getStateMachinePort().getStateMachine().setState(State.chooseRepeat);
		} else {
			this.stateMachine.getStateMachinePort().getStateMachine().setState(State.getNextPlayer);
		}
	}

	@Override
	public void handleAnswerWrong(Figure figureToMove) {
		this.questions.getQuestionManagerPort().getKnowledgeIndicatorManager().decrease(util.getCurrentPlayer(),
				util.getCurrentCategory());
		this.game.getGameModelPort().getGameModel().moveFigure(figureToMove.getPlayer().getStartingField(),figureToMove);
	}

	@Override
	public void handleMoveFigure() {
		Collision potentialCollision = this.game.getGameModelPort().getGameModel()
				.moveFigure(this.dice.getDicePort().getDice().getLastResult(), this.util.getCurrentFigure());
		if (potentialCollision.collisionHappened()) {
			this.stateMachine.getStateMachinePort().getStateMachine().setState(State.chooseCategory);
		} else {
			this.stateMachine.getStateMachinePort().getStateMachine().setState(State.getNextPlayer);
		}
	}

	@Override
	public void handleCheckAnswer(int controllerInput) {
		if (this.data.currentQuestion.isAnswerCorrect(controllerInput)) {
			this.stateMachine.getStateMachinePort().getStateMachine().setState(State.questionAnsweredCorrectly);
		} else {
			this.stateMachine.getStateMachinePort().getStateMachine().setState(State.questionAnsweredWrong);
		}
	}

	@Override
	public void handleChooseNextQuestion() {
		this.data.currentQuestion = this.questions.getQuestionManagerPort().getQuestionAsker()
				.getNextQuestion(this.util.getCurrentCategory());
		this.stateMachine.getStateMachinePort().getStateMachine().setState(State.checkAnswer);

	}

	@Override
	public void handleChooseCategory(int controllerInput) {
		this.data.currentCategoryIndex = controllerInput;
		this.stateMachine.getStateMachinePort().getStateMachine().setState(State.chooseNextQuestion);
	}

	@Override
	public void handleAddFigureToMatchfield() {
		Collision collision = this.game.getGameModelPort().getGameModel()
				.addFigureForPlayer(this.util.getCurrentPlayer());
		if (collision.collisionHappened()) {
			this.stateMachine.getStateMachinePort().getStateMachine().setState(State.chooseCategory);
		} else {
			this.stateMachine.getStateMachinePort().getStateMachine().setState(State.getNextPlayer);
		}
	}

	@Override
	public void handleChooseFigureInField(int controllerInput) {
		if(figureInFieldIndexValid(controllerInput)) {
			this.data.currentFigureIndex = controllerInput;
			if(this.util.wouldCollideWithFigureFromSamePlayer()) {
				throw new IllegalArgumentException(
						"The chosen figure would collide with a figure of the same player, wich is not allowed."
				);
			} else {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.moveFigure);
			}
		} else {
			throw new IllegalArgumentException(
					"The input is not valid. There are less figures of this player in field than the parameter requires."
			);
		}
	}

	private boolean figureInFieldIndexValid(int figureIndex) {
		return (this.util.getCurrentPlayer().getFiguresInField().length >= figureIndex) && (figureIndex >= 0);
	}

	@Override
	public void handleGetNextPlayer() {
		this.util.IncrCurrentPlayerIndex();
		this.data.diceRollCounter = 0;
		this.stateMachine.getStateMachinePort().getStateMachine().setState(State.throwDice);
	}

	@Override
	public void handleThrowDice() {
		this.dice.getDicePort().getDice().roll();
		this.stateMachine.getStateMachinePort().getStateMachine().setState(State.diceThrown);
	}

	@Override
	public void handleDiceThrown() {
		int result = this.dice.getDicePort().getDice().getLastResult();
		this.data.diceRollCounter++;
		if (util.figureAddingAllowed(result)) {
			if (!util.figureCanBeAdded()) {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.chooseFigureInField);
			} else if (util.figureCanBeAdded()) {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.addFigureToMatchField);
			}
		} else {
			if (util.currentPlayerHasFigureInField()) {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.chooseFigureInField);
			} else if (!util.currentPlayerHasFigureInField() && this.data.diceRollCounter < 3) {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.throwDice);
			} else {
				this.stateMachine.getStateMachinePort().getStateMachine().setState(State.getNextPlayer);
			}
		}
	}

	@Override
	public GamePlayData getGameData() {
		return this.data;
	}

	@Override
	public IDice getGameDice() {
		return this.dice.getDicePort().getDice();
	}

	@Override
	public void handleChooseRepeat(boolean controllerInput) {
		this.reset();
		this.stateMachine.getStateMachinePort().getStateMachine().setState(State.getNextPlayer);
	}

	@Override
	public Player getCurrentPlayer() {
		return this.util.getCurrentPlayer();
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
		return this.questions.getQuestionManagerPort().getQuestionAsker().getAllQuestions();
	}

	@Override
	public QuestionCategory[] getQuestionCategories() {
		return this.questions.getQuestionManagerPort().getQuestionAsker().getQuestionCategories();
	}

	@Override
	public AField[] getFigurePositionsOfPlayer(Player player) {
		return this.game.getGameModelPort().getGameModel().getFigurePositionsOfPlayer(player);
	}

	@Override
	public int getMatchfieldSize() {
		return this.game.getGameModelPort().getGameModel().getMatchfieldSize();
	}

	@Override
	public boolean allFiguresInMatchfield(Player player) {
		return this.game.getGameModelPort().getGameModel().allFiguresInMatchfield(player);
	}

}
