package application.logic.gamemodel.implementation.matchfield;

import java.util.LinkedList;

import application.logic.gamemodel.implementation.player.Figure;
import application.logic.gamemodel.interfaces.AField;

public class StandardField extends AField {

	public StandardField(int fieldCounter) {
		this.occupied = false;
		this.fieldCounter = fieldCounter;
		this.figuresOnThisField = new LinkedList<Figure>();
	}
	
}
