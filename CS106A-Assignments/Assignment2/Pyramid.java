/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	
	
	public void run() {
		
		 /* sets the x and y cords for our bricks
		  * also centers them.
		  */
		double x = getWidth() / 2 - BRICK_WIDTH / 2;
		double y = getHeight() - BRICKS_IN_BASE * BRICK_HEIGHT;
		
		/* counts the rows and lays a new brick row each increment
		 * also moves down the pyramid the height of a brick and 
		 * expands out .5 brick widths
		 */
		for (int i = 0; i < BRICKS_IN_BASE; i++) {
			layBrickRow(x, y, i);
			x -= BRICK_WIDTH /2;
			y += BRICK_HEIGHT;
		}
	}
		
	private void layBrickRow(double x, double y, int rowCount) {
		
		 /* puts a brick at x, y then shifts x over the width of a brick
		  * then repeats
		  */
		for (int i = 0; i <= rowCount; i++) {
			GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
			add(brick);
			x += BRICK_WIDTH;
		}
			
		
		
	}
	
}