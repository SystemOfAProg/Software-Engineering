package application.gui;

import application.logic.gamelogic.GameLogicFactory;
import application.logic.gamelogic.port.IGamePort;
import application.logic.observerandsubject.IObserver;
import application.logic.stateMachine.IState;

public class GUI implements IObserver  {

	// Feld um "getData()" auszuführen (Folie 2, Seite 220)
	IGamePort game;
	
	public GUI(GameLogicFactory logic) {
		this.game = logic.gamePort();
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
		System.out.println("|           Written in 2017-2018 by:                             |");
		System.out.println("|            - Fabian Sorn                                       |");
		System.out.println("|            - Robin Hoffmann                                    |");
		System.out.println("+————————————————————————————————————————————————————————————————+");
		System.out.println();
	}
	
	private static void printEndSequence() {
		System.out.println();
		System.out.println("+————————————————————————————————————————————————————————————————+");
		System.out.println("|                            ENDE                                |");
		System.out.println("+————————————————————————————————————————————————————————————————+");
	}
	
}
