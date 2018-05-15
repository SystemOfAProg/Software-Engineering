package application.logic.gamemodel.impl.matchfield;

import java.util.LinkedList;

import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;

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
	
	@Override
	public String getFieldIdentifier() {
		return this.getClass().getSimpleName() + " "
				+ this.player.getPlayerCount() + "."
				+ this.fieldCounter;
	}
	
}
