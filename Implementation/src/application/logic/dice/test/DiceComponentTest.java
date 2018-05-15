package application.logic.dice.test;

import org.junit.Before;
import org.junit.Test;

import application.logic.dice.IDiceFactory;
import application.logic.dice.port.IDice;
import application.logic.dice.port.IDicePort;

/** 
 * Tests test Functionalities over Factories.
 * Same Code runs as if used by other components.
 * Not only test components functionality but also wiring of component.
 */
public class DiceComponentTest {

	private IDicePort dicePort;
	
	@Before 
	public void init() {
		this.dicePort = IDiceFactory.FACTORY.getDicePort();       
    }
	
	@Test
	public void testStandardDice() {
		IDice dice = this.dicePort.getDice();
		int diceResult;
		for(int i=0; i<500; i++) {
			diceResult = dice.roll();
			assert(diceResult >= 1 && diceResult <=6);
		}
	}

}
