/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private final static int SENTINEL = 0;
	
	public void run() {
		int n = 1;
		int low = 1000000;
		int high = -1000000;
		
		println("This program finds the largest and smallest numbers.");
		
		while (n != SENTINEL) {
			n = readInt("? ");
			if (n == SENTINEL && high == -1000000) {
				println("Why so ready to go?");
			}
			
			if (n > high) {
				high = n;
			} else if  (high == -1000000 && n == SENTINEL){
				high = low;
			}
			
			if (n < low && n != SENTINEL) {
				low = n;
			}else if  (low == 1000000 && n == SENTINEL){
				low = high;
			}
		}
		
		println("smallest: " + low);
		println("largest: " + high);
	}
}

