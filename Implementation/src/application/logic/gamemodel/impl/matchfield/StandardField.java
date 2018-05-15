package application.logic.gamemodel.impl.matchfield;

import java.util.LinkedList;

import application.logic.gamemodel.impl.AField;
import application.logic.gamemodel.impl.player.Figure;

public class StandardField extends AField {

	public StandardField(int fieldCounter) {
		this.occupied = false;
		this.fieldCounter = fieldCounter;
		this.figuresOnThisField = new LinkedList<Figure>();
	}
	
}
