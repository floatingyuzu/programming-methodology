/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.*;
import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		drawScaffolding();
		incorrectGuessCount = 0;
		wrongGuesses = "";
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* remove the whole current label if it exists*/
		if(getElementAt(TEXT_SPACER, SCAFFOLD_HEIGHT * 1.2) != null) {
			GObject gobj = getElementAt(TEXT_SPACER, SCAFFOLD_HEIGHT * 1.2);
			remove(gobj);
		}
		
		/* add the replacement label */
		GLabel theWordSoFar = new GLabel(word, TEXT_SPACER, SCAFFOLD_HEIGHT * 1.2);
		theWordSoFar.setFont(new Font("", Font.BOLD, 18));
		add(theWordSoFar);
		
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		
		/* track how many times we done the method */
		incorrectGuessCount++;
		
		/* add the incorrect letter to the screen */
		wrongGuesses += " " + letter;
		
		
		GLabel incorrectGuess = new GLabel(wrongGuesses, TEXT_SPACER, SCAFFOLD_HEIGHT * 1.3);
		add(incorrectGuess);
		
		/* Add the Body Parts for missed guess */
		switch(incorrectGuessCount) {
			case 1:	GOval head = new GOval((getWidth() /2) - (HEAD_RADIUS), SCAFFOLD_Y_OFFSET + ROPE_LENGTH, 
											HEAD_RADIUS * 2, HEAD_RADIUS * 2); 
					add(head);
					break;
					
			case 2:	GLine body = new GLine(getWidth() /2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2), 
		 									getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH);
		 			add(body);
					break;
			case 3:	GLine leftArm = new GLine(getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD,
												(getWidth() / 2) - UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD);	
					GLine leftHand = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD,
												(getWidth() / 2) - UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD
																																		  + LOWER_ARM_LENGTH);
					add(leftArm);
					add(leftHand);
					break;
			case 4:	GLine rightArm = new GLine(getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD,
											(getWidth() / 2) + UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD);		
					GLine rightHand = new GLine(getWidth() / 2 + UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD,
												(getWidth() / 2) + UPPER_ARM_LENGTH, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD
																																		 + LOWER_ARM_LENGTH);	
			
					add(rightArm);
					add(rightHand);
					break;
			case 5:	GLine leftHip = new GLine(getWidth() / 2, yDistanceToHips, 
												(getWidth() / 2) - (HIP_WIDTH), yDistanceToHips);
					GLine leftLeg = new GLine((getWidth() / 2) - (HIP_WIDTH), yDistanceToHips, 
												(getWidth() / 2) - (HIP_WIDTH), yDistanceToHips + LEG_LENGTH);
					add(leftHip);
					add(leftLeg);
					break;
			case 6:	GLine rightHip = new GLine((getWidth() / 2), yDistanceToHips, 
												(getWidth() / 2) + (HIP_WIDTH), yDistanceToHips);
					GLine rightLeg = new GLine((getWidth() / 2) + (HIP_WIDTH), yDistanceToHips, 
												(getWidth() / 2) + (HIP_WIDTH), yDistanceToHips + LEG_LENGTH);
					add(rightHip);
					add(rightLeg);
					break;
			case 7:	GLine leftFoot = new GLine((getWidth() / 2) - (HIP_WIDTH), yDistanceToHips + LEG_LENGTH, 
												(getWidth() / 2) - (HIP_WIDTH) - FOOT_LENGTH, yDistanceToHips + LEG_LENGTH);
					add(leftFoot);
					break;
			case 8:	GLine rightFoot = new GLine((getWidth() / 2) + (HIP_WIDTH), yDistanceToHips + LEG_LENGTH, 
												(getWidth() / 2) + (HIP_WIDTH) + FOOT_LENGTH, yDistanceToHips + LEG_LENGTH);
					GArc mouth = new GArc((getWidth() - MOUTH_WIDTH) / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS + (HEAD_RADIUS / 8), MOUTH_WIDTH, MOUTH_HEIGHT, 0, 180);
					GLabel leftEye = new GLabel("X");
					GLabel rightEye = new GLabel("X");
					leftEye.setFont(new Font("", Font.ITALIC, 14));
					leftEye.setLocation(((getWidth() - leftEye.getWidth()) / 2) - (HEAD_RADIUS /4), SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS / 4) + leftEye.getHeight());
					rightEye.setFont(new Font("", Font.ITALIC, 14));
					rightEye.setLocation(((getWidth() - rightEye.getWidth()) / 2) + (HEAD_RADIUS /4), SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS / 4) + rightEye.getHeight());
					add(mouth);
					add(rightFoot);
					add(leftEye);
					add(rightEye);
					break;
			
		
			
			
		
		}
	}
	
	private void drawScaffolding() {
		
		GLine scaffold = new GLine((getWidth() / 2) - BEAM_LENGTH, SCAFFOLD_Y_OFFSET, (getWidth() / 2) - BEAM_LENGTH, SCAFFOLD_Y_OFFSET + SCAFFOLD_HEIGHT);
		GLine beam = new GLine(getWidth() / 2, SCAFFOLD_Y_OFFSET, (getWidth() / 2) - BEAM_LENGTH, SCAFFOLD_Y_OFFSET);
		GLine rope = new GLine(getWidth() / 2, SCAFFOLD_Y_OFFSET, getWidth() / 2, SCAFFOLD_Y_OFFSET + ROPE_LENGTH);
		
		add(scaffold);
		add(rope);
		add(beam);
	}
	
	
	
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_Y_OFFSET = 20; // offset for the top of the screen
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int MOUTH_WIDTH = 40;
	private static final int MOUTH_HEIGHT = 30;
	private static final int TEXT_SPACER = 20;  // offset for the left of the screen
	
/* Instance Variables */
	private int incorrectGuessCount = 0;
	private int yDistanceToHips = SCAFFOLD_Y_OFFSET + ROPE_LENGTH + (HEAD_RADIUS * 2) + BODY_LENGTH;
	private String wrongGuesses = "";
	

}
