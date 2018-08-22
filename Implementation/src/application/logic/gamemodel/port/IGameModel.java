package application.logic.gamemodel.port;

import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.matchfield.Matchfield;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;
import application.logic.gamesettings.port.IGameModelSettings;

public interface IGameModel {

	// ~~~~~~~~~~~~~~~~~ Read State ~~~~~~~~~~~~~~~~~~

	/**
	 * Returns all players included in the current game.
	 * @return All Players as Array
	 */
	Player[] getPlayers();
	
	/**
	 * Returns the current matchfield.
	 * @return Matchfield
	 */
	Matchfield getMatchfield();
	
	/**
	 * Looks up where all figures of a specific players figures are located and
	 * return their position.
	 * @param player Player whose figures position should be searched
	 * @return Fields the figures are placed on as array
	 */
	AField[] getFigurePositionsOfPlayer(Player player);
	
	/**
	 * Get count of fields of the Matchfield excluding HomeFields
	 * @return count of starting- and standard fields
	 */
	int getMatchfieldSize();
	
	/**
	 * Checks if all figures of a player are in the matchfield.
	 * No figure is left over in the Home-Fields
	 * @param player Player whose figures should be looked for
	 * @return True if all figures are in the matchifield
	 */
	boolean allFiguresInMatchfield(Player player);

	// ~~~~~~~~~~~~~~~~~ Write State ~~~~~~~~~~~~~~~~~~
	
	/**
	 * Move a specific figure a specific distance
	 * @param steps How many Fields should the figure move?
	 * @param figure Which Figure should be moved?
	 * @return Collision Object that represents the state and information if collision happened
	 */
	Collision moveFigure(int steps, Figure figure);

	/**
	 * Move a specific figure to a specific field
	 * @param moveHere Where is the destination of this figure
	 * @param figure Which Figure should be moved?
	 * @return Collision Object that represents the state and information if collision happened
	 */
	Collision moveFigure(AField moveHere, Figure figure);

	/**
	 * Add a Figure from outside the matchfield to the actual matchfield
	 * @param player Player whose figure shall be added
	 * @return Collision Object representing the potential Collision that happens at the figure movement
	 */
	Collision addFigureForPlayer(Player player);
	
	/**
	 * Reset the complete Game Model and set everything to its initial state
	 */
	void reset();
	
	/**
	 * Apply settings and reset the current game to the initial states given by the settings object
	 * @param settings Settings that should be used for the game when resetting
	 */
	void setSettingsAndReset(IGameModelSettings settings);
	
}
