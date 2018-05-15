package application.logic.dice;

import application.logic.dice.port.IDicePort;

// Facade and Factory for Dice
public interface IDiceFactory {

	// Interface already provides implementation for IDiceFactory
	// -> Factory as central possibility for using the Dice Interface
	IDiceFactory FACTORY = new DiceFactory();

	// Provide all Dice Ports 
	IDicePort getDicePort();
	
}
