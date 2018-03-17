package application.logic.gamemodel.matchfield;

import application.logic.gamemodel.*;
import application.logic.gamemodel.player.Player;

public class Matchfield {

	private AField[] fields;
	private Game game;
	
	public Matchfield(int matchfieldSize, int playerCount, Game game) {
		this.fields = new AField[matchfieldSize];
		for(int i=0; i<matchfieldSize; i++) {
			this.fields[i] = new StandardField();
		}
	}

	public AField[] getAllFields() {
		return this.fields;
	}
	
	public StartingField setStartFiledForPlayer(Player Player) {
		return new StartingField();
	}
	
}
