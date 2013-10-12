/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	private static final double OUTER_RED_CIRCLE = 1.0;
	
	private static final double MIDDLE_WHITE_CIRCLE = 0.65;

	private static final double INNER_RED_CIRCLE = 0.3;
	
	private static final double SIZE_RATIO = 72;
	
	public void run() {
		
		drawOuterRed();
		drawMiddleWhite();
		drawInnerRed();
	}
	
	private void drawOuterRed() {
		double x = (getWidth() - (OUTER_RED_CIRCLE * SIZE_RATIO)) / 2;
		double y = (getHeight() - (OUTER_RED_CIRCLE * SIZE_RATIO)) / 2;
		double size = OUTER_RED_CIRCLE * SIZE_RATIO;
		GOval outerRed = new GOval(x, y, size, size);
		outerRed.setColor(Color.WHITE);
		outerRed.setFillColor(Color.RED);
		outerRed.setFilled(true);
		add(outerRed);
	}
	
	private void drawMiddleWhite() {
		double x = (getWidth() - (MIDDLE_WHITE_CIRCLE * SIZE_RATIO)) / 2;
		double y = (getHeight() - (MIDDLE_WHITE_CIRCLE * SIZE_RATIO)) / 2;
		double size = MIDDLE_WHITE_CIRCLE * SIZE_RATIO;
		GOval middleWhite = new GOval(x, y, size, size);
		middleWhite.setColor(Color.WHITE);
		middleWhite.setFillColor(Color.WHITE);
		middleWhite.setFilled(true);
		add(middleWhite);
	}
	
	private void drawInnerRed() {
		double x = (getWidth() - (INNER_RED_CIRCLE * SIZE_RATIO)) / 2;
		double y = (getHeight() - (INNER_RED_CIRCLE * SIZE_RATIO)) / 2;
		double size = INNER_RED_CIRCLE * SIZE_RATIO;
		GOval innerRed = new GOval(x, y, size, size);
		innerRed.setColor(Color.WHITE);
		innerRed.setFillColor(Color.RED);
		innerRed.setFilled(true);
		add(innerRed);
	}
}
