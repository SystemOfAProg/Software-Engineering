package application.logic.gamemodel.implementation.matchfield;

import application.logic.gamemodel.implementation.player.*;
import application.logic.gamemodel.implementation.Game;
import application.logic.gamemodel.implementation.matchfield.*;
import application.logic.gamemodel.implementation.questions.*;
import application.logic.gamemodel.interfaces.AField;
import application.logic.questionmanager.serialization.QuestionReader;

public class Matchfield {

	private AField[] fields;
	private Game game;
	
	public Matchfield(int matchfieldSize, int playerCount, Game game) {
		this.fields = new AField[matchfieldSize];
		for(AField sf: this.fields) {
			sf = new StandardField();
		}
	}

	public AField[] getAllFields() {
		return this.fields;
	}
	
	public StartingField setStartFieldForPlayer(Player player) {
		StartingField stf = new StartingField(player);
		this.fields[0] = stf;
		return stf;
	}
	
}
