/*
 * File: Artistry.java
 * Name:
 * Section Leader:
 * ==========================================================
 * Replace these comments with a description of your program.
 * Since this program is more freeform than the rest, tell us
 * a bit about it in these comments!
 */
import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

public class Artistry extends GraphicsProgram {
	public void run() {
		/* You fill this in.  Remember that you must have
		 * 
		 * 1. At least one filled object,
		 * 2. At least two different colors of objects, and
		 * 3. At least three different types of objects (not
		 *    counting the GLabel you use to sign your name).
		 * 
		 * Also, be sure to sign your name in the bottom-right
		 * corner of the canvas.
		 */
		
		GOval circle = new GOval(100, 75, 50, 50);
		circle.setFilled(true);
		add(circle);
		
		GOval circle2 = new GOval(125, 100, 50, 50);
		circle2.setFilled(true);
		circle2.setFillColor(Color.GREEN);
		add(circle2);
		
		GRect square = new GRect(150, 125, 50, 50);
		add(square);
		
		GLine line = new GLine(25, 0, 200, 175);
		add(line);
		
		GLine line2 = new GLine(25, 0, 150, 175);
		add(line2);
		
		GLine line3 = new GLine(25, 0, 200, 125);
		add(line3);
		
		GLine line4 = new GLine(25, 0, 150, 125);
		add(line4);
		
		GLabel name = new GLabel("Ben Mills");
		name.setLocation(getWidth() - name.getWidth(), getHeight());
		add(name);
		
	}
}
