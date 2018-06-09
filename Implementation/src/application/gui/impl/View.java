package application.gui.impl;

import application.gui.port.IObserver;
import application.gui.port.IView;
import application.logic.gamelogic.GameLogicFactory;
import application.logic.gamelogic.IGameLogicFactory;
import application.logic.gamelogic.port.IGamePlay;
import application.logic.gamelogic.port.IGameStart;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.StandardField;
import application.logic.gamemodel.impl.matchfield.StartingField;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.questionmanager.impl.Answer;
import application.logic.questionmanager.impl.Question;
import application.logic.stateMachine.port.IState;
import application.logic.stateMachine.port.IState.State;

public class View implements IObserver, IView {

	// IGameLogicFactory as outer Logic Component
	// -> Facade for operations on Model
	// -> Every Operation should be accessible from here
	private IGameLogicFactory logic;
	private Controller controller;
	
	public View(IGameLogicFactory logic) {
		this.logic = IGameLogicFactory.FACTORY;
	}

	@Override
	public void addGameLogic(IGameLogicFactory logic) {
		this.logic = logic;
		this.logic.attach(this);
		this.controller = new Controller(this.logic,this);
	}

	@Override
	/**
	 * React on an updated State from the Statemachine, read input from console
	 * if necessary and invoke the corresponding functionality in the game logic
	 */
	public void update(IState state) {
		IGamePlay gamePlay = this.logic.getGamePort().getGamePlay();
		IGameStart gameStart = this.logic.getGamePort().getGameStart();
		if (state.isSubStateOf(State.GameActive)) {
			if (state == State.getNextPlayer) {
				this.printCurrentPlayer();
			} else if (state == State.throwDice) {
				this.printRollDice();
			} else if (state == State.chooseFigureInField) {
				// TODO read game model from logic and print current state
			} else if (state == State.addFigureToMatchField) {
				// TODO read game model from logic and print current state
			} else if (state == State.chooseCategory) {
				// TODO read game model from logic and print current state
			} else if (state == State.chooseNextQuestion) {
				// TODO read game model from logic and print current state
			} else if (state == State.checkAnswer) {
				// TODO read game model from logic and print current state
			} else if (state == State.moveFigure) {
				// TODO read game model from logic and print current state
			} else if (state == State.adjustIndicators) {
				// TODO read game model from logic and print current state
			}
		} else if (state.isSubStateOf(State.ChooseSettings)) {
			if(state == State.showTutorial) {
				// TODO read game model from logic and print current state
			} else if (state == State.useStandardSettings) {
				// TODO read game model from logic and print current state
			} else if (state == State.choosePlayerCount) {
				// TODO read game model from logic and print current state
			} else if (state == State.chooseFieldsPerPlayer) {
				// TODO read game model from logic and print current state
			} else if (state == State.chooseFiguresPerPlayer) {
				// TODO read game model from logic and print current state
			} else if (state == State.chooseKnowledgeInditcatorSize) {
				// TODO read game model from logic and print current state
			}
		} else if (state.isSubStateOf(State.GameCompleted)) {
			if (state == State.chooseRepeat) {
				// TODO read game model from logic and print current state
			}
		} else {
			throw new IllegalStateException("The current State \"" + state + "\" of the state-machine ist not valid.");
		}
	}

	private void printCurrentPlayer() {
		System.out.println("Current Player: " + this.logic.getGamePort().getGamePlay().getCurrentPlayer().getPlayerName());
	}

	private void printRollDice() {
		System.out.println("Rolling the Dice");
	}

	// ==================== Printing functions ====================
	
	private static void printStartSequence() {
		System.out.println("+————————————————————————————————————————————————————————————————+");
		System.out.println("|        _____ _    _  _    _  _    _______ _   _ _    _         |");
		System.out.println("|       |_   _| |  | || |  | || |  | |  _  \\ \\ | | |  | |        |");
		System.out.println("|         | | | |  | || |  | || |  | | | | |  \\| | |  | |        |");
		System.out.println("|         | | | |/\\| || |/\\| || |/\\| | | | | . ` | |/\\| |        |");
		System.out.println("|        _| |_\\  /\\  /\\  /\\  /\\  /\\  / |/ /| |\\  \\  /\\  /        |");
		System.out.println("|        \\___/ \\/  \\/  \\/  \\/  \\/  \\/|___/ \\_| \\_/\\/  \\/         |");
		System.out.println("|                Ich weiß was, was du nicht weißt©               |");
		System.out.println("+————————————————————————————————————————————————————————————————+");
		System.out.println();
	}
	
	private static void printEndSequence() {
		System.out.println();
		System.out.println("+————————————————————————————————————————————————————————————————+");
		System.out.println("|                            ENDE                                |");
		System.out.println("+————————————————————————————————————————————————————————————————+");
	}

	public void printField() {
		AField[] fields = this.logic.getGamePort().getGamePlay().getMatchfield().getAllFields();
		for(AField field: fields) {
			String fieldViewContent = "";
			if(!(field.getFigures().isEmpty())) {
				for(Figure occupant: field.getFigures()) {
					fieldViewContent += Integer.toString(occupant.getPlayer().getPlayerCount());
				}
			} else {
				fieldViewContent = " ";
			}
			if(StartingField.class.isInstance(field)) {
				System.out.println("> ( " + fieldViewContent + " ) " + field.getFieldIdentifier() );				
			} else if (StandardField.class.isInstance(field)){
				System.out.println("> ( "+ fieldViewContent +" ) " + field.getFieldIdentifier());
			}
		}		
	}
	
	public void printCurrentState(Player[] players) {
		// TODO: print summary of the current status of the game
		for(Player player: players) {
			System.out.println("Informationen zu " + player.getPlayerName());
			System.out.println(" | --- Figuren:");
			for(Figure figure: player.getFigures()) {
				System.out.println(" |     |--- " + figure.getFigureIdentifier());
				System.out.println(" |     |         | --- Location: " + figure.getCurrentLocation().getFieldIdentifier());
			}
		}
	}
	
	public void printQuestions(Question[] questions) {
		for(Question q: questions) {
			System.out.println("------------------------------------------------------------------------");
			System.out.println("|--- Question:   '"+ q.getQuestionSentence() + "'");
			System.out.println("|--- Answers:");
			for (Answer a: q.getAnswers()) {
				System.out.println("|    |--- Answer: '"+ a.getAnswerSentence() + "'");
			}
			System.out.println("|--- Category: '" + q.getCategory().getName() + "'");
			System.out.println("|--- Correct Answer: '" + q.getCorrectAnswer().getAnswerSentence() + "'");
			System.out.println("------------------------------------------------------------------------");
			System.out.println();
		}
	}
	
	public void printQuestionCountAndLocation(Question[] questions, String fileLocation) {
		System.out.println("> " + questions.length + " questions found in " + fileLocation);
	}
}
