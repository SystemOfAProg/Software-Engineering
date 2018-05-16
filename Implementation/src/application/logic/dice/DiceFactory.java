package application.logic.dice;

import application.logic.dice.impl.Dice;
import application.logic.dice.port.IDice;
import application.logic.dice.port.IDicePort;

public class DiceFactory implements IDiceFactory, IDicePort, IDice {

	private IDice dice;
	
	// Lazy Creation of concrete Dice instance
	private void mkDice() {
		if (this.dice == null) {
			this.dice = new Dice();
		}
	}
	
	// Implementation IDiceFactory	
	@Override
	public IDicePort getDicePort() {
		return this;
	}
	
	// Implementation IDicePort
	@Override
	public IDice getDice() {
		this.mkDice();
		return this;
	}

	// Implementation IDice
	@Override
	public int roll() {
		this.mkDice();
		return this.dice.roll();
	}

	@Override
	public int getLastResult() {
		this.mkDice();
		return this.dice.getLastResult();
	}

}
