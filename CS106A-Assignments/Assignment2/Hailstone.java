/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = readInt("Give a number: ");
		int counter = 0;
		while (n != 1) {
			if (n % 2 == 0) {
				int even = n / 2;
				println(n +"     is even, so I take half:     " + even);
				n = even;
			} else {
				int odd = (n * 3) + 1;
				println(n +"     is odd, so I make 3n + 1:     " + odd);
				n = odd;
			}
			counter++;		
		}
		println("The process took " + counter + " to reach 1");
	}
}

