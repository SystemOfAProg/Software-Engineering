package application.logic.dice;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	@Test
	public void testStandardDice() {
		Dice dice = new Dice();
		int diceResult;
		for(int i=0; i<500; i++) {
			diceResult = dice.roll();
			assert(diceResult >= 1 && diceResult <=6);
		}
	}
	
	@Test
	public void testDiceWithBounds() {
		Dice dice = new Dice(24,64);
		int diceResult;
		for(int i=0; i<500; i++) {
			diceResult = dice.roll();
			assert(diceResult >= 24 && diceResult <=64);
		}
	}

}
