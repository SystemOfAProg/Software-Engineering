package application.logic.dice.impl;

import java.util.Random;

import application.logic.dice.port.IDice;

public class Dice implements IDice{

	private Random random;
	private int lowerBound;
	private int upperBound;
	private int lastResult;
	
	public Dice() {
		this(6,1);
	}
	
	public Dice(int upperBound, int lowerBound) {
		if(!(upperBound > lowerBound)) {
			throw new IllegalArgumentException("The upper bound of the dice must be greate than the lower one.");
		}
		this.upperBound = upperBound;
		this.lowerBound = lowerBound;
		this.lastResult = this.lowerBound - 1;
		this.random = new Random();
	}
	
	@Override
	public int roll() {
		this.lastResult = (random.nextInt(upperBound - lowerBound) + lowerBound);
		return this.lastResult;
	}

	@Override
	public int getLastResult() {
		if((this.lastResult <= this.upperBound) && (this.lastResult >= this.lowerBound)) {
			return this.lastResult;			
		} else {
			throw new IllegalStateException("Last result could not be read. The dice has never been thrown.");
		}
	}

	@Override
	public int getLowerBound() {
		return this.lowerBound;
	}

	@Override
	public int getUpperBound() {
		return this.upperBound;
	}
	
}
