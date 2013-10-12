/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	
	public void run() {
		goGetBeeper();
		goBackHome();
        
}

	/* Goes to the beeper and grabs it */
	private void goGetBeeper(){
		move();
        turnRight();
        move();
        turnLeft();
        move();
        move();
        pickBeeper();
	}
	
	/* Goes back home */
	private void goBackHome(){
		turnAround();
		move();
		move();
		move();
		turnRight();
		move();
		turnRight();
	}
	
	
}
