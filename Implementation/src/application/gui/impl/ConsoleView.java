package application.gui.impl;

import application.gui.port.IObserver;
import application.gui.port.IView;
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

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConsoleView implements IObserver, IView {

	// IGameLogicFactory as outer Logic Component
	// -> Facade for operations on Model
	// -> Every Operation should be accessible from here
	private IGameLogicFactory logic;
	private ConsoleController consoleController;

	public ConsoleView() { }

	@Override
	public void startView(IGameLogicFactory logic, String[] args) {
		this.logic = logic;
		this.logic.attach(this);
		this.consoleController = new ConsoleController(this.logic,this);
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
				if(this.logic.getGamePort().getGamePlay().getGameData().roundCounter != 0) {
					this.pressEnterToContinue();
				}
				this.logic.getGamePort().getGamePlay().getGameData().roundCounter++;
				this.printCurrentPlayer();
				this.printField();
			} else if (state == State.throwDice) {
				this.printRollDice();
			} else if (state == State.diceThrown) {
				this.printDiceResults();
			} else if (state == State.chooseFigureInField) {
				this.printFiguresInFieldForCurrentPlayer();
			} else if (state == State.addFigureToMatchField) {
				// TODO read game model from logic and print current state
				System.out.println("##### Add Figure To Matchfield #####");
			} else if (state == State.chooseCategory) {
				// TODO read game model from logic and print current state
				System.out.println("##### Choose Question Category #####");
			} else if (state == State.chooseNextQuestion) {
				// TODO read game model from logic and print current state
				System.out.println("##### Choose Next Question #####");
			} else if (state == State.checkAnswer) {
				// TODO read game model from logic and print current state
				System.out.println("##### Check Given Answer #####");
			} else if (state == State.moveFigure) {
				// TODO read game model from logic and print current state
				System.out.println("##### Move Figure #####");
			} else if (state == State.adjustIndicators) {
				// TODO read game model from logic and print current state
				System.out.println("##### Adjust Knowledge Indicators #####");
			}
		} else if (state.isSubStateOf(State.ChooseSettings)) {
			if(state == State.showTutorial) {
				// TODO read game model from logic and print current state
				System.out.println("##### Show Tutorial #####");
			} else if (state == State.useStandardSettings) {
				// TODO read game model from logic and print current state
				System.out.println("##### Use Standard Settings? #####");
			} else if (state == State.choosePlayerCount) {
				// TODO read game model from logic and print current state
				System.out.println("##### Choose Player Count #####");
			} else if (state == State.chooseFieldsPerPlayer) {
				// TODO read game model from logic and print current state
				System.out.println("##### Choose Fields Per Player #####");
			} else if (state == State.chooseFiguresPerPlayer) {
				// TODO read game model from logic and print current state
				System.out.println("##### Choose Figures Per Player #####");
			} else if (state == State.chooseKnowledgeInditcatorSize) {
				// TODO read game model from logic and print current state
				System.out.println("##### choose Knoledge Indicator Size #####");
			}
		} else if (state.isSubStateOf(State.GameCompleted)) {
			if (state == State.chooseRepeat) {
				// TODO read game model from logic and print current state
				System.out.println("##### Choose if game should be repeated #####");
			}
		} else {
			throw new IllegalStateException("The current State \"" + state + "\" of the state-machine ist not valid.");
		}
	}

	// Wait for enter input for next step
	private void pressEnterToContinue() {
		System.out.println("");
		System.out.println("                 ––– Press ENTER to continue ––––                 ");
		System.out.println("");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Wait n seconds before continuing execution
	private void waitForSeconds(int n) {
		try {
			TimeUnit.SECONDS.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void printCurrentPlayer() {
		String currentPlayer = String.format("%-45s", this.logic.getGamePort().getGamePlay().getCurrentPlayer().getPlayerName());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("+————————————————————————————————————————————————————————————–————+");
		System.out.println("| ||||||||||||||||||||||| New Round ||||||||||||||||||||||||||||| |");
		System.out.println("+—————————————————————————————————————————————————————————————–———+");
		System.out.println("|   Current Player: " + currentPlayer                         + " |");
		System.out.println("+—————————————————————————————————————————————————————————————–———+");
		System.out.println();
	}

	private void printRollDice() {
		System.out.println("			   +––––––––––––––––––––––––––––––+   ");
		System.out.println("               |        ____                  |   ");
		System.out.println("               |       /\\' .\\    _____        | ");
		System.out.println("               |      /: \\___\\  / .  /\\       |");
		System.out.println("               |      \\' / . / /____/..\\      | ");
		System.out.println("               |       \\/___/  \\'  '\\  /      |");
		System.out.println("               |                \\'__'\\/       | ");
		System.out.println("			   +––––––––––––––––––––––––––––––+   ");
		System.out.println("               |        Rolling the Dice      |   ");
	}

	private void printDiceResults() {
		int result = this.logic.getGamePort().getGamePlay().getGameDice().getLastResult();
		String resultPadded = String.format("%-19s", result);
		System.out.println("               |  Result: " + resultPadded      + " |   ");
		System.out.println("			   +––––––––––––––––––––––––––––––+   ");
		this.waitForSeconds(1);
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
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
		System.out.println("|    Figure |  Field Identifier                                   |");
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
		for(AField field: fields) {
			String fieldViewContent = "";
			if(!(field.getFigures().isEmpty())) {
				for(Figure occupant: field.getFigures()) {
					fieldViewContent += occupant.getFigureIdentifier() + " ";
				}
			}
			String fieldViewContentPadded = String.format("%9s", fieldViewContent );
			String currentPositionPadded = String.format("%-50s", field.getFieldIdentifier());
			System.out.println("| " + fieldViewContentPadded + " |  " + currentPositionPadded + " |");
		}
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
	}

	private void printFiguresInFieldForCurrentPlayer() {
		Figure[] figures = this.logic.getGamePort().getGamePlay().getCurrentPlayer().getFigures();
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
		System.out.println("|   Figure  |  Current Position                                   |");
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
		for(int i=0; i<figures.length; i++) {
			String currentPosition = figures[i].getCurrentLocation().getFieldIdentifier();
			String currentIndexPadded = String.format("%7s", i+1 );
			String currentPositionPadded = String.format("%-50s", currentPosition);
			System.out.println("|  " + currentIndexPadded + "  |  " + currentPositionPadded + " |");
		}
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
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
		System.out.println(questions.length + " questions found in " + fileLocation);
	}

	public void showInputBoolean(String questionToAsk) {
		System.out.println(questionToAsk + ": (Y/N)");
		System.out.print("> ");
	}

	@Override
	public void showRetryInput(Exception exception) {
		System.err.print(exception.getMessage() + " Please retry with an other input:\n> ");
	}

	@Override
	public void showInputNumber(int min, int max) {
		System.out.println("Please insert a number: (" + min + "..." + max + ")");
		System.out.print("> ");
	}

}
