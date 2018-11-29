package application.logic.gamemodel.impl;

import java.util.LinkedList;

import application.logic.gamemodel.impl.matchfield.Collision;
import application.logic.gamemodel.impl.player.Figure;
import application.logic.gamemodel.impl.player.Player;

public abstract class AField {

	protected LinkedList<Figure> figuresOnThisField;
	protected int fieldCounter;
	
	public Collision setOccupied(Figure newFigure) {
		Collision collision;
		if( !this.figuresOnThisField.isEmpty()) {
			collision = new Collision(this.figuresOnThisField.getFirst(), newFigure, this);
		} else {
			collision = new Collision();
		}
		this.figuresOnThisField.add(newFigure);
		return collision;
	}
	
	public void removeOccupied(Figure figure) {
		this.figuresOnThisField.remove(figure);
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

	public boolean isOccupied() {
		return this.figuresOnThisField.size() > 0;
	}
	
}
