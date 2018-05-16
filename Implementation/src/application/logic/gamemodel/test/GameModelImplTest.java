package application.logic.gamemodel.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.GameModel;
import application.logic.gamemodel.impl.matchfield.HomeField;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamemodel.port.IGameModel;

/**
 * Tests for provided implementation of Game Model
 */
public class GameModelImplTest {

	public int playerCount = 4;
	public int fieldsPerPlayer = 10;
	public int figuresPerPlayer = 3;
	public int knowledgeIndicatorSize = 5;
	
	// ======================== Tests ======================
	
	@Test
	public void testGameParameterization() {
		// standardField
		GameModel game = new GameModel(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
		assertEquals(game.getMatchfieldSize(), 				   (this.fieldsPerPlayer * this.playerCount));
		assertEquals(game.getPlayers().length, 					this.playerCount);
		assertEquals(game.getPlayers()[0].getFigures().length, 	this.figuresPerPlayer);
		// non standard
		GameModel game2 = new GameModel(this.fieldsPerPlayer + 2, this.playerCount + 1, this.figuresPerPlayer + 3, this.knowledgeIndicatorSize);
		assertEquals(game2.getMatchfieldSize(), 				   ((this.fieldsPerPlayer+2)  * (this.playerCount+1)));
		assertEquals(game2.getPlayers().length, 					(this.playerCount+1));
		assertEquals(game2.getPlayers()[0].getFigures().length, 	(this.figuresPerPlayer+3));
	}

	@Test
	public void testMatchfieldMovementOverflow() {
		GameModel game = new GameModel(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
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
	public void testGameModelResetFigurePositions() {
		GameModel game = new GameModel(this.fieldsPerPlayer, this.playerCount, this.figuresPerPlayer, this.knowledgeIndicatorSize);
		this.addAllFigures(game);
		this.moveAllFiguresOnField(game);
		for(Player player: game.getPlayers()) {
			assertTrue(game.allFiguresInMatchfield(player));
		}
		game.reset();
		for(Player player: game.getPlayers()) {
			assertFalse(game.allFiguresInMatchfield(player));
			for(Figure figure: player.getFigures()) {
				assertTrue(figure.getCurrentLocation() instanceof HomeField);
			}
		}
		for(AField field: game.getMatchfield().getAllFields()) {
			assertFalse(field.isOccupied());
		}
		this.addAllFigures(game);
		for(Player player: game.getPlayers()) {
			assertTrue(game.allFiguresInMatchfield(player));
		}
	}
	
	// ====================== Utility Functions for some tasks ======================
	
	/**
	 * Add all figures ov every player to the matchfield.
	 * @param game Instance of Game Object
	 */
	public void addAllFigures(IGameModel game) {
		for(Player player: game.getPlayers()) {
			for(@SuppressWarnings("unused") Figure figure: player.getFigures()) {
				game.addFigureForPlayer(player);
			}
		}
	}
	
	/**
	 * Move all figures on field randomly to simulate round of playing with a standard dice.
	 * Collisions between figures can happen and are not handeled.
	 * @param game Instance of Game Object
	 */
	public void moveAllFiguresOnField(IGameModel game) {
		for(Player player: game.getPlayers()) {
			for(Figure figure: player.getFigures()) {
				// Figure is on field
				if(!(figure.getCurrentLocation() instanceof HomeField)) {
					int steps = ((new Random()).nextInt(5) + 1);
					game.moveFigure(steps, figure);
				}
			}
		}
	}

}
