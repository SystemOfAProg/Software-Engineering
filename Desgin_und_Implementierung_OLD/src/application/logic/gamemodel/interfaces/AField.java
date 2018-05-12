package application.logic.gamemodel.interfaces;

import java.util.Collection;
import java.util.LinkedList;

import application.logic.gamemodel.implementation.matchfield.Collision;
import application.logic.gamemodel.implementation.player.Figure;
import application.logic.gamemodel.implementation.player.Player;

public abstract class AField {

	protected boolean occupied;
	protected LinkedList<Figure> figuresOnThisField;
	protected int fieldCounter;
	
	public Collision setOccupied(Figure newFigure) {
		this.occupied = true;
		Collision collision;
		if( !this.figuresOnThisField.isEmpty()) {
			Player playerOnField = this.figuresOnThisField.getFirst().getPlayer();
			collision = new Collision(playerOnField, newFigure.getPlayer(), this);
		} else {
			collision = new Collision();
		}
		this.figuresOnThisField.add(newFigure);
		return collision;
	}
	
	public void removeOccupied(Figure figure) {
		this.figuresOnThisField.remove(figure);
		this.occupied = this.figuresOnThisField.isEmpty();
	}
	
	public int getFieldCounter() {
		return this.fieldCounter;
	}
	
	public String getFieldIdentifier() {
		return this.getClass().getSimpleName() + " "
				+ this.fieldCounter;
	}

	public LinkedList<Figure> getFigures() {
		return this.figuresOnThisField;
	}
	
}
