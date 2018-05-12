package application.logic.gamemodel.implementation.matchfield;

import java.util.LinkedList;

import application.logic.gamemodel.implementation.player.Figure;
import application.logic.gamemodel.implementation.player.Player;
import application.logic.gamemodel.interfaces.AField;

public class HomeField extends AField {

	private Player player;
	
	public HomeField(Player player, int fieldCounter) { 
		this.player = player;
		this.occupied = true;
		this.fieldCounter = fieldCounter;
		this.figuresOnThisField = new LinkedList<Figure>();
	}
	
	public Player getOwner(){
		return this.player;
	}
	
	public String getFieldIdentifier() {
		return this.getClass().getSimpleName() + " "
				+ this.player.getPlayerCount() + "."
				+ this.fieldCounter;
	}
	
}
