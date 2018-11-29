package application.logic.gamemodel.impl.matchfield;

import java.util.LinkedList;

import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;

public class StartingField extends AField {

	private Player player;
	
	public StartingField(Player player, int fieldCounter) { 
		this.player = player;
		this.fieldCounter = fieldCounter;
		this.figuresOnThisField = new LinkedList<Figure>();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	@Override
	public String getFieldIdentifier() {
		return this.getClass().getSimpleName() + " "
				+ this.player.getPlayerCount() + "."
				+ this.fieldCounter;
	}
	
}
