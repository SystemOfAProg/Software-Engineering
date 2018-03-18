package application.logic.gamemodel.matchfield;

import application.logic.gamemodel.player.Player;

public class HomeField extends AField {

	private Player player;
	
	public HomeField(Player player) { 
		this.player = player;
		this.occupied = true;
	}
	
	
	
}
