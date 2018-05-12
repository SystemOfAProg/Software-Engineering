package application;

import java.awt.print.Printable;

import application.logic.gamemodel.implementation.Game;

// Meta-Klasse "System" aus Foliensatz 2, Seite 221
public class Main {

	public static void main(String[] args) {
		// ~~~~~ Setup Process ~~~~~
		// Create Model
		// Create View with reference to model
		// View attaches itself to the model
		// View creates a controller with reference to itself and the model
		// Controller attaches itself to the Model
		// ~~~~~ Start Event Loop ~~~~~
		// Start the actual Game
		printStartSequence();
		printEndSequence();
	}
	
	private static void printStartSequence() {
		System.out.println(" ---------------------------------------------------------------- ");
		System.out.println("|        _____ _    _  _    _  _    _______ _   _ _    _         |");
		System.out.println("|       |_   _| |  | || |  | || |  | |  _  \\ \\ | | |  | |        |");
		System.out.println("|         | | | |  | || |  | || |  | | | | |  \\| | |  | |        |");
		System.out.println("|         | | | |/\\| || |/\\| || |/\\| | | | | . ` | |/\\| |        |");
		System.out.println("|        _| |_\\  /\\  /\\  /\\  /\\  /\\  / |/ /| |\\  \\  /\\  /        |");
		System.out.println("|        \\___/ \\/  \\/  \\/  \\/  \\/  \\/|___/ \\_| \\_/\\/  \\/         |");
		System.out.println("|                Ich weiss was, was du nicht weisst              |");
		System.out.println(" ---------------------------------------------------------------- ");
		System.out.println("|           Written in WS 2017-2018 by:                          |");
		System.out.println("|            - Fabian Sorn                                       |");
		System.out.println("|            - Robin Hoffmann                                    |");
		System.out.println(" ---------------------------------------------------------------- ");
		System.out.println();
	}
	
	private static void printEndSequence() {
		System.out.println();
		System.out.println(" ---------------------------------------------------------------- ");
		System.out.println("|                            ENDE                                |");
		System.out.println(" ---------------------------------------------------------------- ");
	}

}
