/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

/*
 * Spiraling technique.  Start in 1-1 and lay a row of beepers turning left at 
 * each corner.  After 3 turns we need to make sure Karel doesn't lay beepers
 * on other beepers.  If he keeps turning left he'll get to the "center" of the rectangle.
 * 
 * We can then have Karel face South and go until he hits 1-X, the midpoint.  
 * From there he's going to work his way through the level again picking beepers
 * in a back and forth direction avoiding the midpoint beeper.  He'll finish picking
 * aproaching the midpoint beeper from the rear and then when he detects no beepers 
 * he will back up and place a beeper at the Midpoint 1-X.
 * 
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	
public void run() {
	
	if (frontIsBlocked()) { // this protects Karel if the world has only 1 width.
		putBeeper();
	} else {
		
		setBoundryBeepers();  // puts beepers on East and West walls
		while (noBeepersPresent()) {  // keeps putting beepers as long as none are presest a
			bounceToMiddle();		  // alternating from East to West
		}
			cleanUp();	// discards beepers East of midpoint
			goal();	// discards beepers West of midpoint and goes back to midpoint
	}
}
private void setBoundryBeepers() {
	putBeeper();
	while (frontIsClear()) {
		move();
	}
	putBeeper();
	bounce();
}

private void bounceToMiddle() {
	while (noBeepersPresent()) {
		move();
	}
	bounce();
	putBeeper();
	move();
}

private void bounce() {
	turnAround();
	move();
}

private void cleanUp() {
	while (frontIsClear()) {
		pickBeeper();
		move();
	}
	pickBeeper();
	turnAround();
	while (noBeepersPresent()) {
		move();
	}
}
private void goal() {
	move();
	cleanUp();
	
}

}

