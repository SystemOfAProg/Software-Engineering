package application;

import java.awt.print.Printable;

import application.logic.gamemodel.implementation.Game;

// Start for application "Ich wei�, was was du nicht wei�."
public class Main {

	public static void main(String[] args) {
		// TODO: Generate new Game with all components
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
		System.out.println("|                Ich wei� was, was du nicht weiߩ                |");
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
