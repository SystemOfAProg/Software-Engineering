package application.gui.impl;

import application.gui.port.IObserver;
import application.gui.port.IView;
import application.logic.gamelogic.GameLogicFactory;
import application.logic.gamelogic.IGameLogicFactory;
import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.StandardField;
import application.logic.gamemodel.impl.matchfield.StartingField;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.questionmanager.impl.Answer;
import application.logic.questionmanager.impl.Question;
import application.logic.stateMachine.port.IState;

public class View implements IObserver, IView {

	// IGameLogicFactory as outer Logic Component
	// -> Facade for operations on Model
	// -> Every Operation should be acessible from here
	private IGameLogicFactory gameLogicFactory;
	
	public View(GameLogicFactory logic) {
		this.gameLogicFactory = IGameLogicFactory.FACTORY;
	}
	
	@Override
	public void update(IState newSate) {
		// TODO: React to changes in Logic depending of the given state
		
	}
	
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
	
	public void printField(AField[] fields) {
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
