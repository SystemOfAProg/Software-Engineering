package application.gui.impl;

import application.gui.port.IObserver;
import application.gui.port.IView;
import application.logic.gamelogic.IGameLogicFactory;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.questionmanager.impl.Answer;
import application.logic.questionmanager.impl.KnowledgeIndicator;
import application.logic.questionmanager.impl.Question;
import application.logic.questionmanager.impl.QuestionCategory;
import application.logic.stateMachine.port.IState;
import application.logic.stateMachine.port.IState.State;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
		if (state.isSubStateOf(State.GameActive)) {
			this.updateGameActive(state);
		} else if (state.isSubStateOf(State.ChooseSettings)) {
			this.updateChooseSettings(state);
		} else if (state.isSubStateOf(State.GameCompleted)) {
			this.updateGameCompleted(state);
		} else {
			throw new IllegalStateException("The current State \"" + state + "\" of the state-machine ist not valid.");
		}
	}

	public void updateChooseSettings(IState state) {
		if(state == State.showTutorial) {
			this.printStartSequence();
		} else if (state == State.choosePlayerCount) {
			System.out.println("How many players do you want to add?");
		} else if (state == State.chooseFieldsPerPlayer) {
			System.out.println("How many fields per player do you want to add to the match-field?");
		} else if (state == State.chooseFiguresPerPlayer) {
			System.out.println("How many fields per figures do you want to add per player?");
		} else if (state == State.chooseKnowledgeInditcatorSize) {
			System.out.println("How many steps do you want in your Knowledge Indicators?");
		}
	}

	public void updateGameActive(IState state) {
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
			this.printAddFigureToMatchfield();
		} else if (state == State.chooseCategory) {
			this.printQuestionCategories();
		} else if (state == State.chooseNextQuestion) {
			// question print after controller chose next question -> print question in next state
		} else if (state == State.checkAnswer) {
			this.printNextQuestion();
		} else if (state == State.questionAnsweredCorrectly) {
			this.printQuestionAnsweredRight();
		} else if (state == State.questionAnsweredWrong) {
			this.printQuestionAnsweredWrong();
		} else if (state == State.knowledgeIndicatorAdjusted) {
			this.printKnowledgeIndicatorAdjusted();
		}
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

	private void printEndSequence() {
	    System.out.println();
	    System.out.println("Congratulations, " +
                this.logic.getGamePort().getGamePlay().getCurrentPlayer().getPlayerName() +
                " has won the game!");
		System.out.println();
		System.out.println("+————————————————————————————————————————————————————————————————+");
		System.out.println("|                            ENDE                                |");
		System.out.println("+————————————————————————————————————————————————————————————————+");
	}

	private void printCurrentPlayer() {
		String currentPlayer = String.format("%-45s", this.logic.getGamePort().getGamePlay().getCurrentPlayer().getPlayerName());
		String currentRoundNumber = String.format("%3s", this.logic.getGamePort().getGamePlay().getGameData().roundCounter).replace(" ", "0");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("+————————————————————————————————————————————————————————————–————+");
		System.out.println("| ||||||||||||||||||||||| Round " + currentRoundNumber + " ||||||||||||||||||||||||||||| |");
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

	private void printAddFigureToMatchfield() {
		System.out.println("+—————————————————————————————————————————————————————————————————+");
		System.out.println("|                  Hooray, you have thrown a 6.                   |");
		System.out.println("|     If possible, we add an other figure to the matchfield.      |");
		System.out.println("+—————————————————————————————————————————————————————————————————+");
	}

	private void printQuestionCategories() {
		QuestionCategory[] categories = this.logic.getGamePort().getGamePlay().getQuestionCategories();
		System.out.println("Please choose a category, whose question you want to be answered:");
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
		System.out.println("|   Number  |  Question Category                                  |");
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
		for(int i=0; i<categories.length; i++) {
			String currentIndexPadded = String.format("%7s", i+1 );
			String categoryPadded = String.format("%-50s", categories[i].getName());
			System.out.println("|  " + currentIndexPadded + "  |  " + categoryPadded + " |");
		}
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
	}

	private void printNextQuestion() {
		Question currentQuestion = this.logic.getGamePort().getGamePlay().getGameData().currentQuestion;
		String currentQuestionString = this.formatQuestionSentence(currentQuestion.getQuestionSentence());
		Answer[] answersToCurrentQuestion = currentQuestion.getAnswers();
		System.out.println("+—————————————————————————————————————————————————————————————————+");
		System.out.println( currentQuestionString );
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
		for(int i=0; i<answersToCurrentQuestion.length; i++) {
			String currentIndexPadded = String.format("%7s", i+1 );
			String categoryPadded = String.format("%-50s", answersToCurrentQuestion[i].getAnswerSentence());
			System.out.println("|  " + currentIndexPadded + "  |  " + categoryPadded + " |");
		}
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
	}

	private String formatQuestionSentence(String question) {
		int partCount = (question.length() / 63) + 1;
		String[] substrings = new String[partCount];
		question = String.format("%-" + partCount * 63 + "s", question);
		for (int i = 0; i < question.length(); i += 64) {
				substrings[i/63] = question.substring(i, Math.min(i + 63, question.length()));
				substrings[i/63] = "| " + substrings[i/63] + " |";
		}
		return String.join("\n", substrings);
	}

	private void printQuestionAnsweredWrong() {
		System.out.println("Sorry, you seem to have answered this question wrong. Your Knowledge");
		System.out.println("Indicators now look like this:");
	}

	private void printQuestionAnsweredRight() {
		System.out.println("Woohoo. You have answered the question correclty. Your Knowledge");
		System.out.println("Indicators now look like this:");
	}

	public void printKnowledgeIndicatorAdjusted() {
		QuestionCategory[] categories = this.logic.getGamePort().getGamePlay().getQuestionCategories();
		Player currentPlayer = this.logic.getGamePort().getGamePlay().getCurrentPlayer();
		KnowledgeIndicator[] indicators = this.logic.getGamePort().getGamePlay().getKnowledgeIndicatorsFor(currentPlayer);
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
		System.out.println("|   Value   |  Knowledge Indicator                                |");
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
		for(QuestionCategory category: categories) {
			int value = 0;
			for(KnowledgeIndicator ki: indicators) {
				if(ki.getCategory().equals(category)) {
					value = ki.getCurrentPosition();
				}
			}
			String valuePadded = String.format("%7s", value );
			String categoryPadded = String.format("%-50s", category.getName());
			System.out.println("|  " + valuePadded + "  |  " + categoryPadded + " |");
		}
		System.out.println("+———————————+—————————————————————————————————————————————————————+");
	}

	private void updateGameCompleted(IState state) {
		if (state == State.chooseRepeat) {
			this.printEndSequence();
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
		System.out.println(questions.length + " questions found in " + fileLocation);
	}

	// ========================= Interaction Prints =========================

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

	public void showInputBoolean(String questionToAsk) {
		System.out.println(questionToAsk + ": (Y/N)");
		System.out.print("> ");
	}

	@Override
	public void showRetryInput(Exception exception) {
		System.err.print(exception.getMessage() + " Please retry with an other input.\n> ");
	}

	@Override
	public void showInputNumber(int min, int max) {
		System.out.println("Please insert a number: (" + min + "..." + max + ")");
		System.out.print("> ");
	}

	// Wait n seconds before continuing execution
	private void waitForSeconds(int n) {
		try {
			TimeUnit.SECONDS.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
