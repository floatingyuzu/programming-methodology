/* FILE practiceIO2.java
 * --------------------
 * written by Mehran Sahami CS106A
 * copied by Ben Mills 
 */

import acm.program.*;
import acm.util.*;
import java.io.*;




public class practiceIO2 extends ConsoleProgram{


	private BufferedReader openFile(String prompt) {

	     BufferedReader rd = null;

	     while(rd == null) {
	          try {
	               String name = readLine(prompt);
	               rd = new BufferedReader(new FileReader(name));
	          } catch (IOException ex) {
	               println("Nice try punk.  That file doesn't exist!");
	          }
	     }
	     return rd;
	}
	

	public void run() {
	     BufferedReader rd = openFile("Please enter filename: ");
	     
	     try {
	    	 PrintWriter wr = new PrintWriter(new FileWriter("copy.txt"));
	    	 
	    	 
	          while(true) {
	               String line = rd.readLine();
	               if (line == null) break;
	               
	               println("Copying line: ["  + line + "]");  // write to console what is being copied
	               wr.println(line);  // write to file
	          }
	          
	          rd.close();
	          wr.close();
	          
	     } catch (IOException ex) {
	          throw new ErrorException(ex);  // use for exceptions and you don't know what kind it will be
	     }
	}
	           
	
}

