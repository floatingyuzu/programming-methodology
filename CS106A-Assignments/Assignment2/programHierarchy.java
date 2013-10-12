
import acm.program.*;
import acm.graphics.*;

public class programHierarchy extends GraphicsProgram {
	
	private static final double RECT_HEIGHT = 50;
	
	private static final double RECT_WIDTH = 150;
	

	public void run() {
		/* set x y to be the top left of the centered rectangle
		 * 
		 */
		double x = (getWidth() - RECT_WIDTH) / 2;
		double y = RECT_HEIGHT;
		
		/*
		 * Rectangles:
		 */
		
		GRect program = new GRect(x , y, RECT_WIDTH, RECT_HEIGHT);
		add(program);
		
		GRect consoleProgram = new GRect(x , y * 3, RECT_WIDTH, RECT_HEIGHT);
		add(consoleProgram);
		
		GRect dialogProgram = new GRect(x + (RECT_WIDTH *1.15), y * 3, RECT_WIDTH, RECT_HEIGHT);
		add(dialogProgram);
		
		GRect graphicsProgram = new GRect(x - (RECT_WIDTH *1.15), y * 3, RECT_WIDTH, RECT_HEIGHT);
		add(graphicsProgram);
		
		/*
		 * Labels:
		 */
		
		GLabel programLabel = new GLabel("Program");
		programLabel.setLocation((getWidth() - programLabel.getWidth()) /2 , ((RECT_HEIGHT * 3) + programLabel.getAscent()) / 2);
		add(programLabel);
		
		GLabel cPLabel = new GLabel("ConsoleProgram");
		cPLabel.setLocation((getWidth() - cPLabel.getWidth()) /2 , ((RECT_HEIGHT * 7) + cPLabel.getAscent()) / 2);
		add(cPLabel);
		
		GLabel dPLabel = new GLabel("DialogProgram");
		dPLabel.setLocation(((getWidth() - dPLabel.getWidth()) /2) + (RECT_WIDTH *1.15) , ((RECT_HEIGHT * 7) + dPLabel.getAscent()) / 2);
		add(dPLabel);
		
		GLabel gPLabel = new GLabel("GraphicsProgram");
		gPLabel.setLocation(((getWidth() - gPLabel.getWidth()) /2) - (RECT_WIDTH *1.15) , ((RECT_HEIGHT * 7) + gPLabel.getAscent()) / 2);
		add(gPLabel);
		
		/*
		 * Lines:
		 */
		
		GLine programToConsoleProgram = new GLine(getWidth() / 2, RECT_HEIGHT * 2, getWidth() / 2, RECT_HEIGHT * 3);
		add(programToConsoleProgram);
		
		GLine programToDialogProgram = new GLine(getWidth() / 2, RECT_HEIGHT * 2, (getWidth() / 2) + (RECT_WIDTH * 1.15), RECT_HEIGHT * 3);
		add(programToDialogProgram);
		
		GLine programToGraphicsProgram = new GLine(getWidth() / 2, RECT_HEIGHT * 2, (getWidth() / 2) - (RECT_WIDTH * 1.15), RECT_HEIGHT * 3);
		add(programToGraphicsProgram);
		
	}
	
}
