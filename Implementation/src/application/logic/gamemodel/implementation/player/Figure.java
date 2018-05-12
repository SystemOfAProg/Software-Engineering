package application.logic.gamemodel.implementation.player;

import application.logic.gamemodel.implementation.matchfield.Collision;
import application.logic.gamemodel.implementation.matchfield.HomeField;
import application.logic.gamemodel.interfaces.AField;

public class Figure {

	private Player player;
	private AField currentLocation;
	private int figureCount;
	
	public Figure(Player player, AField currentLocation, int figureCount) {
		this.player = player;
		this.currentLocation = currentLocation;
		this.figureCount = figureCount;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public AField getCurrentLocation() {
		return this.currentLocation;
	}
	
	public String getFigureIdentifier() {
		return "figure " + this.player.getPlayerCount() + "." + this.figureCount;
	}
	
	/**
	 * Moves a figure to a given location and sets 
	 * @param newLocation
	 * @return
	 */
	public Collision move(AField newLocation) {
		this.currentLocation.removeOccupied(this);
		this.currentLocation = newLocation;
		return this.currentLocation.setOccupied(this);
	}
	
	public boolean isInField() {
		return !(this.currentLocation instanceof HomeField);
	}
	
}
