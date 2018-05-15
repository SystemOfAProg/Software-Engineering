package application.logic.gamemodel.impl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import application.logic.gamemodel.impl.Game;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;

public class FigureMovementTests {

	public int playerCount = 4;
	public int fieldsPerPlayer = 10;
	public int figuresPerPlayer = 3;
	public int knowledgeIndicatorSize = 5;
	
	@Test
	public void testGameParameterization() {
		// standardField
		Game game = new Game(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
		assertEquals(game.getMatchfieldSize(), 					(this.fieldsPerPlayer * this.playerCount));
		assertEquals(game.getPlayers().length, 					this.playerCount);
		assertEquals(game.getPlayers()[0].getFigures().length, 	this.figuresPerPlayer);
		// non standard
		Game game2 = new Game(this.fieldsPerPlayer + 2, this.playerCount + 1, this.figuresPerPlayer + 3, this.knowledgeIndicatorSize);
		assertEquals(game2.getMatchfieldSize(), 					((this.fieldsPerPlayer+2)  * (this.playerCount+1)));
		assertEquals(game2.getPlayers().length, 					(this.playerCount+1));
		assertEquals(game2.getPlayers()[0].getFigures().length, 	(this.figuresPerPlayer+3));
	}

	@Test
	public void testMatchfieldMovementOverflow() {
		Game game = new Game(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
		Player player = game.getPlayers()[0];
		Figure figure = player.getFigures()[0];
		game.addFigureForPlayer(player);
		// move to last field of matchfield
		game.moveFigure((game.getMatchfieldSize()-1), figure);
		assertEquals((game.getMatchfieldSize()-1), figure.getCurrentLocation().getFieldCounter());
		// move one further
		game.moveFigure(1, figure);
		assertEquals(0, figure.getCurrentLocation().getFieldCounter());
	}
	
	@Test
	public void testAddingNewPlayers() {
		Game game = new Game(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
		for(Player player: game.getPlayers()) {
			for(Figure figure: player.getFigures()) {
				game.addFigureForPlayer(player);				
			}
		}
		for(int i=0; i<this.playerCount; i++) {
			try {
				// Add another figure although all figure are already in field
				game.addFigureForPlayer(game.getPlayers()[i]);
				fail("Another figure should not be addable in this state for player " + game.getPlayers()[0]);
			} catch (IllegalAccessError iae) {
				assert(true);
			}
		}
	}
	
	@Test
	public void testCollisionAndOccupationOrder() {
		Game game = new Game(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
		Player player1 = game.getPlayers()[0];
		Player player2 = game.getPlayers()[1];
		Collision collision1 = game.addFigureForPlayer(player1);
		assert(!collision1.collisionHappened());
		Collision collision2 = game.addFigureForPlayer(player2);
		assert(!collision2.collisionHappened());
		assert(!player1.getFigures()[0].getCurrentLocation().equals(player2.getFigures()[0].getCurrentLocation()));
		Collision collision3 = game.moveFigure(this.fieldsPerPlayer, player1.getFigures()[0]);
		assert(collision3.collisionHappened());
		game.printField();
		// Test Order of Players in which they were added to the field
		assertEquals(collision3.getFirstPlayer().getPlayerCount(),  player2.getPlayerCount());
		assertEquals(collision3.getSecondPlayer().getPlayerCount(), player1.getPlayerCount());
	}
}
