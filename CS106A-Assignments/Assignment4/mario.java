/*
 * FILE: mario.java
 * ----------------
 * testing grahics
 */


import acm.program.*;
import acm.graphics.*;
import java.awt.*;


public class mario extends GraphicsProgram {
	public void run() {
		setBackground(Color.BLACK);
		GImage mario = new GImage("pixel-mario.gif");
		
		add(mario);
	}
}
