package application.logic.gamemodel.implementation.matchfield;

import application.logic.gamemodel.implementation.player.*;
import application.logic.gamemodel.implementation.Game;
import application.logic.gamemodel.implementation.matchfield.*;
import application.logic.gamemodel.implementation.questions.*;
import application.logic.gamemodel.interfaces.AField;
import application.logic.questionmanager.serialization.QuestionReader;

public class Matchfield {

	private AField[] fields;
	private int playerFieldRatio;
	private Game game;
	
	public Matchfield(int gameFieldSize, int playerCount, Game game) {
		this.playerFieldRatio = (gameFieldSize / playerCount);
		this.fields = new AField[gameFieldSize];
		System.out.println("> Create matchfield of size " + this.fields.length);
		for(int i=0; i<this.fields.length; i++) {
			this.fields[i] = new StandardField(i);
		}
	}

	public AField[] getAllFields() {
		return this.fields;
	}
	
	public int getSize() {
		return this.fields.length;
	}
	
	public StartingField setStartFieldForPlayer(Player player) {
		int startingFieldIndex = player.getPlayerCount() * this.playerFieldRatio;
		this.fields[startingFieldIndex] = new StartingField(player, startingFieldIndex);
		return (StartingField)this.fields[startingFieldIndex];
	}

	public AField calculateNewField(AField oldField, int steps) {
		AField newField;
		if(oldField instanceof StandardField || oldField instanceof StartingField) {
			newField = fields[((oldField.getFieldCounter() + steps) % this.fields.length)];
		} else {
			newField = oldField;
		}
		return newField;
	}
	
}
