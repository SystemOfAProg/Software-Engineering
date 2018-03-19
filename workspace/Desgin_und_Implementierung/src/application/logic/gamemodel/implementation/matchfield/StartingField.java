package application.logic.gamemodel.implementation.matchfield;

import application.logic.gamemodel.implementation.player.Player;
import application.logic.gamemodel.interfaces.*;

public class StartingField extends AField {

	private Player player;
	
	public StartingField(Player player) { 
		this.player = player;
		this.occupied = true;
	}
	
}
