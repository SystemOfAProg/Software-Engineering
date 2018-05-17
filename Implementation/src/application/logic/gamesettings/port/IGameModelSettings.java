package application.logic.gamesettings.port;

/**
 * Interface for holding settings for the game model
 * @author fabiansorn
 */
public interface IGameModelSettings {

	public final int DEFAULT_FIELDSPERPLAYER = 10;
	public final int DEFAULT_PLAYERCOUNT = 4;
	public final int DEFAULT_FIGURESPERPLAYER = 4;
	
	/**
	 * Set how many fields on the matchfield exists per player.
	 * 4 Players & 10 Fields per Players -> Matchfield has 40 Fields
	 * @param fieldsPerPlayer Fields per Player
	 */
	public void setFieldsPerPlayer(int fieldsPerPlayer);
	
	/**
	 * Get how many Fields per Player have been set
	 * @return Fields per Player
	 */
	public int  getFieldsPerPlayer();
	
	/**
	 * Sets how many players are added to the game.
	 * @param playerCount Count of Players
	 */
	public void setPlayerCount(int playerCount);
	
	/**
	 * Returns how many players the model should have
	 * @return Count of Players
	 */
	public int  getPlayerCount();
	
	/**
	 * Sets how many figures a player should have.
	 * @param figuresPerPlayer Count of Figures per Player
	 */
	public void setFiguresPerPlayer(int figuresPerPlayer);
	
	/**
	 * Gets how many Figures Per Player should be added to the Game Model
	 * @return How many Figures a Player should have
	 */
	public int  getFiguresPerPlayer();

	/**
	 * Reset Values to defaults
	 */
	public void resetModelSettings();
		
}
