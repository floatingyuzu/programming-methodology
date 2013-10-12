/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import acm.util.*;

public class HangmanLexicon {

/* Constructor for HangmanLexicon */
	public HangmanLexicon() {
		lexicon = new ArrayList<String>();
		try {
			rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			String line = "";

		    while(line != null) {
		    	line = rd.readLine();
				lexicon.add(line);
		    }
		} catch (IOException ex) {
			throw new ErrorException(ex); 
		} 
	}
	
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lexicon.get(index);
		
	}
	
	/* Instance Variables */
	ArrayList<String> lexicon;
	BufferedReader rd;

}

