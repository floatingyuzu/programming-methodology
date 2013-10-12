/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 * 
 * Written by Ben Mills March 2013.
 */

import acm.program.*;
import acm.util.*;


public class Hangman extends ConsoleProgram {
	
	/*Static Variables */
	private static final int GUESSES_PER_GAME = 8;

	public void init() {
		
        canvas = new HangmanCanvas();
        add(canvas);
	}
	
    public void run() {
    	String playAgain = "Y";
    	currentLexicon = new HangmanLexicon();
    	while(playAgain.charAt(0) == 'Y' || playAgain.charAt(0) == 'y') {
    		resetThings();	
	    	greetPlayer();
	 		getNextWord();
			playGame();
			
			playAgain = readLine("Play again? (Y for yes, or any other key to quit): ");
			
    	}
    	
    	println("Goodbye.");
	}
   
    /* Displayed at the beginning of the game */
    private void greetPlayer() {
    	println("Welcome to Hangman!");
    }
    
    /* Get a new word randomly from the lexicon list.
     * Also initializes the word to dashes. */
    private void getNextWord() {
    	word = currentLexicon.getWord(rgen.nextInt(0, currentLexicon.getWordCount()));
    	initializeWordLooksLikeThis();
    
    }
    
    /*Setting wordLooksLikeThis to all dashes*/
    private void initializeWordLooksLikeThis() {
    	for(int i = 0; i < word.length(); i++) {
    		wordLooksLikeThis += "-";
    	}
    	canvas.displayWord(wordLooksLikeThis);

    }

    private void playGame() {
    	int guessesLeft = GUESSES_PER_GAME;
    	
    	while(true){
    		
    		getNewGuess(guessesLeft);
        	
        	guessChar = guess.charAt(0);
        	guessCharUpper = Character.toUpperCase(guessChar);
        	guessCharLower = Character.toLowerCase(guessChar);
        	
        	repeatGuessCheck();
        	
        	if (checkGuess()) {
        		println("That guess is correct");
        		updateWordLooksLikeThis();
        		updateTotalGuessesSoFar();
        		if(youAreAWinner()) {
        			println("You guessed the word: " + word);
        			println("You win.");
        			break;
        		}
        	} else {
        		println("There are no " + guess + "'s in the word.");
        		guessesLeft--;
        		updateTotalGuessesSoFar();
        		canvas.noteIncorrectGuess(guessCharUpper);
        		if(guessesLeft == 0) {
        			println("Your're completely hung.");
        			println("The word was: " + word);
        			println("You lose.");
        			break;
        		}
        	}
    	}
    }
    
    /* Gives the current standings to the player and takes a new guess */
    private void getNewGuess(int guessesLeft) {
    	println("The word now looks like this: " + wordLooksLikeThis);
    	println("Your have " + guessesLeft + " guesses left.");
    	while(true) {
	    		guess = readLine("Your guess: ");
	    		if (validGuess()) {
	    			break;
	    		}
	    		println("Please enter a valid letter.");
    	}
    	
    }
    
    /* Makes sure the guess is in the range of alphabet letters */
    private boolean validGuess() {
    	if(guess.equals("")) {
    		return false;
    	} else if((guess.charAt(0) >= 'A' && guess.charAt(0) <= 'Z') || 
    	  (guess.charAt(0) >= 'a' && guess.charAt(0) <= 'z')) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /* Alerts the player to repeat guesses*/
    private void repeatGuessCheck() {
    	int repeat = 0;
    	for(int i = 0; i < lettersGuessedSoFar.length(); i++) {
    		if(lettersGuessedSoFar.charAt(i) == guessCharLower || lettersGuessedSoFar.charAt(i) == guessCharUpper) {
    			repeat++;
    		} 
    	}
    	if(repeat > 0) {
    		println("Oops, you guessed that one already!");
    	}
    }
    
    private void updateTotalGuessesSoFar() {
    	lettersGuessedSoFar += guessChar;
    }
    
    /* Scans the guess to see if any correct letters exist */
    private boolean checkGuess() {
    	for(int i = 0; i < word.length(); i++) {
    		if(word.charAt(i) == guessCharLower || word.charAt(i) == guessCharUpper) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /* Replaces the appropriate dashes with guessed letter. */
    private void updateWordLooksLikeThis() {
    	for(int i = 0; i < word.length(); i++) {
    		if(word.charAt(i) == guessCharLower || word.charAt(i) == guessCharUpper) {
    			wordLooksLikeThis = wordLooksLikeThis.substring(0, i)
    								+ word.charAt(i)
    								+ wordLooksLikeThis.substring(i + 1);
    		}
    	}
    	canvas.displayWord(wordLooksLikeThis);
    }
    
    /* Returns true if there are no dashes left in the wordLooksLikeThis */
    private boolean youAreAWinner() {
    	int dashCounter = 0;
    	for(int i = 0; i < word.length(); i++) {
    		if(wordLooksLikeThis.charAt(i) == '-' ) {
    			dashCounter++;
    		}
    	}
    	if(dashCounter > 0) {
    		return false;
    	} else {
    		return true;
    	}
    	
    }
    
   private void resetThings() {
	   
	   wordLooksLikeThis = "";
	   lettersGuessedSoFar = "";
	   canvas.reset();
   }
    
    
/* Instance Variables */
    private HangmanLexicon currentLexicon;
    private String wordLooksLikeThis = "";
    private String guess;
    private String lettersGuessedSoFar = "";
    private char guessChar;
    private char guessCharUpper;
    private char guessCharLower;
    private String word;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private HangmanCanvas canvas;
}
