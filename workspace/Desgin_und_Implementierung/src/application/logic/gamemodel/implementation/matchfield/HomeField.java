package application.logic.gamemodel.implementation.matchfield;

import application.logic.gamemodel.implementation.player.Player;
import application.logic.gamemodel.interfaces.AField;

public class HomeField extends AField {

	private Player player;
	
	public HomeField(Player player) { 
		this.player = player;
		this.occupied = true;
	}
	
	
	
}
