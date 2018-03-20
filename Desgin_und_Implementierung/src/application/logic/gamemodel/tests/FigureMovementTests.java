package application.logic.gamemodel.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import application.logic.gamemodel.implementation.Game;

public class FigureMovementTests {

	public int playerCount = 4;
	public int fieldsPerPlayer = 10;
	
	@Test
	public void test() {
		Game game = new Game(this.fieldsPerPlayer, this.playerCount);
		game.printCurrentState();
		System.out.println();
		game.printField();
		System.out.println();
		game.addFigureForPlayer(game.getPlayers()[0]);
		game.printField();
		System.out.println();
		game.movePlayer(3, game.getPlayers()[0].getFigures()[0], game.getPlayers()[0]);
		game.printField();
		assertTrue(true);
	}

}
