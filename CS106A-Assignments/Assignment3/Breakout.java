/*
 * File: Breakout.java
 * -------------------
 * Name: Ben Mills
 * 
 * The game Breakout.  
 * It's still a little buggy but getting close.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;
	
	private static final int TOTAL_BRICKS = NBRICKS_PER_ROW * NBRICK_ROWS;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	/** Time in milliseconds to pause movement */
	private static final int DELAY = 10;
	
	/** Random Generator for x direction of ball initial state */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	/** Velocity of the ball's x direction */
	private  double vx = rgen.nextDouble(1.0, 3.0);
					
	/** Velocity of the ball's x direction */
	private  double vy = 3.0;

	

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		drawGameBoard();
		playGame();
	}
	
	private void playGame() {
		addMouseListeners();
		
		while (!gameOver())  {
			drawBall();
			
			while ((ball.getY() + (BALL_RADIUS * 2)) != HEIGHT) {
				moveBall();
				checkForCollision();
				pause(DELAY);
			}
			
			lives--;
			remove(ball);
			pause(DELAY * 50);
		}
	}
	
	
	private void drawGameBoard() {
		drawBrickGrid();
		drawPaddle();
	}
	
	private void drawBrickGrid() {
		for (int i = 0; i < NBRICK_ROWS; i++) {
				drawBrickRow(i);
		}
	}
	
	/*takes in the index of the row based on drawBrickGrid
	 * spaces the bricks and colors them based on their row number*/
	private void drawBrickRow(int rowCount) {
		for (int i = 0; i < NBRICKS_PER_ROW; i++) {
			brick = new GRect(i * (BRICK_WIDTH + BRICK_SEP) + (BRICK_SEP /2), BRICK_Y_OFFSET + (rowCount * BRICK_HEIGHT) + (rowCount * BRICK_SEP), BRICK_WIDTH, BRICK_HEIGHT);
			
			/*setup the color in each row based on the number row*/
			if (rowCount == 0 || rowCount == 1) {
				brick.setFilled(true);
				brick.setColor(Color.RED);
			} else if (rowCount == 2 || rowCount == 3) {
				brick.setFilled(true);
				brick.setColor(Color.ORANGE);
			} else if (rowCount == 4 || rowCount == 5) {
				brick.setFilled(true);
				brick.setColor(Color.YELLOW);
			} else if (rowCount == 6 || rowCount == 7) {
				brick.setFilled(true);
				brick.setColor(Color.GREEN);
			} else {
				brick.setFilled(true);
				brick.setColor(Color.CYAN);
			}
				
			add(brick);
		}
	}
	
	private void drawPaddle() {
		paddle = new GRect((WIDTH - PADDLE_WIDTH) /2, HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	private void drawBall() {
		ball = new GOval((WIDTH - BALL_RADIUS) /2, (HEIGHT - BALL_RADIUS) / 2, BALL_RADIUS, BALL_RADIUS);
		if (rgen.nextBoolean(0.5)) vx = -vx; // this line randomly chooses whether the ball heads left or right initially.
		ball.setFilled(true);
		add(ball);
	}
	
	private void moveBall() {
		ball.move(vx, vy);
		if ((ball.getX() <= 0) || (ball.getX() + BALL_RADIUS) >= WIDTH) {
			vx = -vx;
		} else if ((ball.getY() <= 0) || ((ball.getY() + BALL_RADIUS) >= HEIGHT)) {
			vy = -vy;
		}
	}
	
	/* bounces off the paddle or a brick and breaks the brick
	 * getCollidingObject() sets collider to said object */
	private void checkForCollision() {
		GObject collider = getCollidingObject();
		while (collider != null) {
			if (collider == paddle) {
				vy = -vy;
				break;
			} else {
				vy = -vy;
				remove(collider);
				bricksDestroyed++;
				break;
				
			}
		}
	}
		/*Checks four corners of the ball for an object
		 * returns the object it hit*/
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			gobj = getElementAt(ball.getX(), ball.getY());
			return gobj;
		} else if (getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY()) != null) {
			gobj = getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY());
			return gobj;
		} else if (getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY() + (BALL_RADIUS * 2)) != null) {
			gobj = getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY() + (BALL_RADIUS * 2));
			return gobj;
		} else if (getElementAt(ball.getX(), ball.getY() + (BALL_RADIUS * 2)) != null) {
			gobj = getElementAt(ball.getX(), ball.getY() + (BALL_RADIUS * 2));
			return gobj;
		} else {
			return null;
		}
	}
	
	 /* moves the mouse with the paddle */
	public void mouseMoved(MouseEvent e) {
		 if (e.getX() > PADDLE_WIDTH / 2 && e.getX() < WIDTH - (PADDLE_WIDTH / 2)) {
	            paddle.move(e.getX()-paddle.getX() - (PADDLE_WIDTH / 2), 0);
	        }
	}
	
	private boolean gameOver() {
		return(lives < 1 || bricksDestroyed == TOTAL_BRICKS);
	}
	
	
	/* Instance Variables */
	GObject gobj;
	GOval ball;
	GRect paddle;
	GRect brick;
	int lives = NTURNS;
	int bricksDestroyed = 0;
}
