/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		
		/* for narrow vertical worlds*/
		if (frontIsBlocked()) {
			turnLeft();
			layCheckerRow();
		}
		
		/* for the rest of the worlds*/
		while (frontIsClear()) {
			layCheckerRow();
			goToNextRow();
		}
	
	}
	
	private void layCheckerRow() {	
		while (frontIsClear()) {
				putBeeper();
				move();
				if (frontIsClear()) {
					 move(); 
					 if (frontIsBlocked()) {
						 putBeeper();
					 }
				}
			}
		

	}
	
	private void goToNextRow() {
		if (facingEast()) {
			if (beepersPresent()) {
				// extra move here for odd rows
				if (leftIsClear()) {
					turnLeft();
					move();
					turnLeft();
					move();
				}
			}
			else {
				if (leftIsClear()) {
					turnLeft();
					move();
					turnLeft();
				}
				
			}
		}
		else {
			if (rightIsClear()) {
				turnRight();
				move();
				turnRight();
			}
		}
		 
	}
}
