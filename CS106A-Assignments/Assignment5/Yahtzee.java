/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		for(int i = 0; i < N_SCORING_CATEGORIES; i++) {
			display.printMessage(playerNames[0] + "'s Turn. Please click \"roll dice\" to roll the dice.");

			display.waitForPlayerToClickRoll(1);
			while (rollCount < ROLLS_PER_TURN - 1) {
				display.displayDice(getDiceRoll());
				display.printMessage("Select dice to reroll.");
				display.waitForPlayerToSelectDice();
				rollCount++;
			}
			display.displayDice(getDiceRoll());
			
			display.printMessage("Choose category to apply roll.");
			categoryChosen[i] = display.waitForPlayerToSelectCategory();
			while (score[categoryChosen[i]] != 0) {
				display.printMessage("Not a valid category.  Choose again.");
				categoryChosen[i] = display.waitForPlayerToSelectCategory();
			}
			
			
			if (validateScoreForCategory(categoryChosen[i])) {
				score[i] = calculateScore(categoryChosen[i]);

			} else {
				score[i] = 0;

			}
			
			display.updateScorecard(categoryChosen[i], 1, score[i]);
			resetThings();
		}
		calculateScore();
	}
	
	private int[] getDiceRoll() {
		if (rollCount > 0) {
			for(int i = 0; i < N_DICE; i++) {
					if (display.isDieSelected(i)) {
						dice[i] = rgen.nextInt(1,6);
					}
			}
		} else {
			for(int i = 0; i < N_DICE; i++) {
					dice[i] = rgen.nextInt(1,6);
				}
		}
			return dice;
	}

	private int calculateScore(int category) {
		int amount = 0;
		
			switch (category) {
				
				case ONES: 		amount = ONES * getSingles(ONES);
								break;
								
				case TWOS: 		amount = TWOS * getSingles(TWOS);
								break;

				case THREES:	amount = THREES * getSingles(THREES);
								break;

				case FOURS: 	amount = FOURS * getSingles(FOURS);
								break;

				case FIVES: 	amount = FIVES * getSingles(FIVES);
								break;

				case SIXES: 	amount = SIXES * getSingles(SIXES);
								break;
								
				case THREE_OF_A_KIND: 	amount = totalOfAllDice();
										break;
										
				case FOUR_OF_A_KIND:	amount = totalOfAllDice();
										break;
				
				case FULL_HOUSE:		amount = 25;
										break;
				case SMALL_STRAIGHT:	amount = 30;
										break;
				case LARGE_STRAIGHT:	amount = 40;
										break;
				case YAHTZEE:			amount = 50;
										break;
				case CHANCE:			amount = totalOfAllDice();
										break;
				default: 		amount = 0;
								break;
			}
		
		
		return amount;
	}
	
	private int getSingles(int number) {
		int occouranceCounter = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == number) occouranceCounter++;
		}
		return occouranceCounter;
	}
	
	private int totalOfAllDice() {
		int total = 0;
		for (int i = 0; i < N_DICE; i++) {
			total += dice[i];
		}
		
		return total;
	}
	
	private void totalUpperScore() {
		
		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			if (categoryChosen[i] == ONES || categoryChosen[i] == TWOS || categoryChosen[i] == THREES ||
				categoryChosen[i] == FOURS || categoryChosen[i] == FIVES || categoryChosen[i] == SIXES) {
				upperScore += score[i];
			}
		}
		
		display.updateScorecard(UPPER_SCORE, 1, upperScore);
	}
	
private void totalLowerScore() {
		
		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			if (categoryChosen[i] == THREE_OF_A_KIND || categoryChosen[i] == FOUR_OF_A_KIND || 
				categoryChosen[i] == FULL_HOUSE || categoryChosen[i] == SMALL_STRAIGHT || 
				categoryChosen[i] == LARGE_STRAIGHT || categoryChosen[i] == YAHTZEE || 
				categoryChosen[i] == CHANCE) {
				lowerScore += score[i];
			}
		}
		
		display.updateScorecard(LOWER_SCORE, 1, lowerScore);
	}
	
	private boolean validateScoreForCategory(int category) {
		boolean p;
		switch (category){
			case ONES:			p = YahtzeeMagicStub.checkCategory(dice, ONES);
								break;
			case TWOS:			p = YahtzeeMagicStub.checkCategory(dice, TWOS);
								break;
			case THREES:		p = YahtzeeMagicStub.checkCategory(dice, THREES);
								break;
			case FOURS:			p = YahtzeeMagicStub.checkCategory(dice, FOURS);
								break;	
			case FIVES:			p = YahtzeeMagicStub.checkCategory(dice, FIVES);
								break;
			case SIXES:				p = YahtzeeMagicStub.checkCategory(dice, SIXES);
									break;
			case THREE_OF_A_KIND:	p = YahtzeeMagicStub.checkCategory(dice, THREE_OF_A_KIND);
									break;
			case FOUR_OF_A_KIND:	p = YahtzeeMagicStub.checkCategory(dice, FOUR_OF_A_KIND);
									break;
			case FULL_HOUSE:		p = YahtzeeMagicStub.checkCategory(dice, FULL_HOUSE);
									break;
			case SMALL_STRAIGHT:	p = YahtzeeMagicStub.checkCategory(dice, SMALL_STRAIGHT);
									break;
			case LARGE_STRAIGHT:	p = YahtzeeMagicStub.checkCategory(dice, LARGE_STRAIGHT);
									break;
			case YAHTZEE:	p = YahtzeeMagicStub.checkCategory(dice, YAHTZEE);
							break;
			case CHANCE:	p = YahtzeeMagicStub.checkCategory(dice, CHANCE);
							break;
			default:		p = false;
							break;
			
		}
		
		return p;
	}
	
	
	private void resetThings() {
		rollCount = 0;
	}
	
	private void calculateScore() {
		totalUpperScore();
		if (upperScore >= UPPER_BONUS_THRESHOLD) {
			display.updateScorecard(UPPER_BONUS, 1, 35);
			upperScore += UPPER_BONUS_VALUE;
		} else {
			display.updateScorecard(UPPER_BONUS, 1, 0);
		}
		totalLowerScore();
		display.updateScorecard(TOTAL, 1, upperScore + lowerScore);
	}
	
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] dice = new int[N_DICE];
	private int rollCount = 0;
	int[] categoryChosen = new int[N_SCORING_CATEGORIES];
	int[] score = new int[N_SCORING_CATEGORIES];
	int upperScore = 0;
	int lowerScore = 0;
}
