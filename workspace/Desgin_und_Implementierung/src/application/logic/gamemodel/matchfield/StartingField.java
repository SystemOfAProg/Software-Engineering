package application.logic.gamemodel.matchfield;

import application.logic.gamemodel.player.Player;

public class StartingField extends AField {

	private Player player;
	
	public StartingField(Player player) { 
		this.player = player;
		this.occupied = true;
	}
	
}
