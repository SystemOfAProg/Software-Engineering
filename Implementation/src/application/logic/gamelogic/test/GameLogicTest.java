package application.logic.gamelogic.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import application.logic.gamelogic.IGameLogicFactory;

public class GameLogicTest {

	// Test if instances are the same -> returns Factory always the same instance even
	// when used in two different classes
	@Test
	public void testGameLogicFactoryInstance() {
		IGameLogicFactory glf1 = new GLTest1().gl;
		IGameLogicFactory glf2 = new GLTest2().gl;
		assertTrue(glf1 == glf2);
		glf1 = new GLTest1().gl;
		glf2 = new GLTest1().gl;
		assertTrue(glf1 == glf2);
	}

}
