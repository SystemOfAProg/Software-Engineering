package application.logic.gamemodel.implementation.matchfield;

import java.util.LinkedList;

import application.logic.gamemodel.implementation.player.Figure;
import application.logic.gamemodel.implementation.player.Player;
import application.logic.gamemodel.interfaces.*;

public class StartingField extends AField {

	private Player player;
	
	public StartingField(Player player, int fieldCounter) { 
		this.player = player;
		this.occupied = false;
		this.fieldCounter = fieldCounter;
		this.figuresOnThisField = new LinkedList<Figure>();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public String getFieldIdentifier() {
		return this.getClass().getSimpleName() + " "
				+ this.player.getPlayerCount() + "."
				+ this.fieldCounter;
	}
	
}
