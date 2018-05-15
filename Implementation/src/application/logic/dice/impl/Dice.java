package application.logic.dice.impl;

import java.util.Random;

import application.logic.dice.port.IDice;

public class Dice implements IDice{

	private Random random;
	private int lowerBound;
	private int upperBound;
	
	/**
	 * creates standard Dice with lowerBound=1 and upperBound=6
	 */
	public Dice() {
		this.upperBound = 6;
		this.lowerBound = 1;
		this.random = new Random();
	}
	
	public int roll() {
		return (random.nextInt(upperBound - lowerBound) + lowerBound);
	}
	
}
