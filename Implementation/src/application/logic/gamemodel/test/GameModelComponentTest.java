package application.logic.gamemodel.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import application.logic.gamemodel.IGameModelFactory;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.port.IGameModel;
import application.logic.gamemodel.port.IGameModelPort;

// Tests on interaction level (factory) of other components
public class GameModelComponentTest {

	public int playerCount = 4;
	public int fieldsPerPlayer = 10;
	public int figuresPerPlayer = 3;
	public int knowledgeIndicatorSize = 5;
	
	private IGameModelPort gameModelPort;
	private IGameModel game;
	
	@Before 
	public void init() {
		this.gameModelPort = IGameModelFactory.FACTORY.getGameModelPort();
		this.game = this.gameModelPort.getGameModel();
		this.game.reset();
    }
	
	@Test
	public void testAddingNewPlayers() {
		// Add all existing figures to field
		for(Player player: game.getPlayers()) {
			for(@SuppressWarnings("unused") Figure figure: player.getFigures()) {
				game.addFigureForPlayer(player);		
			}
		}
		for(int i=0; i<this.playerCount; i++) {
			try {
				// Add another figure although all figure are already in field
				game.addFigureForPlayer(game.getPlayers()[i]);
				fail("Another figure should not be addable in this state for player " + game.getPlayers()[0]);
			} catch (IllegalAccessError iae) {
				// Test success when Exception is correctly thrown
				assert(true);
			}
		}
	}
	
	@Test
	public void testCollisionAndOccupationOrder() {
		Player player1 = game.getPlayers()[0];
		Player player2 = game.getPlayers()[1];
		Collision collision1 = game.addFigureForPlayer(player1);
		assert(!collision1.collisionHappened());
		Collision collision2 = game.addFigureForPlayer(player2);
		assert(!collision2.collisionHappened());
		assert(!player1.getFigures()[0].getCurrentLocation().equals(player2.getFigures()[0].getCurrentLocation()));
		Collision collision3 = game.moveFigure(this.fieldsPerPlayer, player1.getFigures()[0]);
		assert(collision3.collisionHappened());
		// Test Order of Players in which they were added to the field
		assertEquals(collision3.getFirstFigure().getPlayer().getPlayerCount(),  player2.getPlayerCount());
		assertEquals(collision3.getSecondFigure().getPlayer(), player1.getPlayerCount());
	}
	
}
