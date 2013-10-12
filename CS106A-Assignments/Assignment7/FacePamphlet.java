/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		setupUI();
    }
    
	private void setupUI() {
		
		// NORTH UI
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(new JLabel("Name "), NORTH);
		add(nameField, NORTH);
		
		addButton = new JButton("Add");
		add(addButton, NORTH);
		addButton.addActionListener(this);
		
		deleteButton = new JButton("Delete");
		add(deleteButton, NORTH);
		deleteButton.addActionListener(this);
		
		lookupButton = new JButton("Lookup");
		add(lookupButton, NORTH);
		lookupButton.addActionListener(this);
		
		// WEST UI
		
		statusField = new JTextField(TEXT_FIELD_SIZE);
	
		add(statusField, WEST);
		statusField.addActionListener(this);
		
		statusButton = new JButton("Change Status");
		add(statusButton, WEST);
		statusButton.addActionListener(this);
		
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		add(pictureField, WEST);
		pictureField.addActionListener(this);
		
		pictureButton = new JButton("Change Picture");
		add(pictureButton, WEST);
		pictureButton.addActionListener(this);
		
		friendField = new JTextField(TEXT_FIELD_SIZE);
		add(friendField, WEST);
		friendField.addActionListener(this);
		
		friendButton = new JButton("Add Friend");
		add(friendButton, WEST);
		friendButton.addActionListener(this);
		
	}
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	
    	String name = nameField.getText();
    	String status = statusField.getText();
    	String picture = pictureField.getText();
    	String friend = friendField.getText();
    	
    	if (e.getSource() == addButton) addProfile(name);
    	else if (e.getSource() == deleteButton) deleteProfile(name);
    	else if (e.getSource() == lookupButton) lookupProfile(name);
		else if (e.getSource() == statusField || e.getSource() == statusButton) setStatus(status);
    	else if (e.getSource() == pictureField || e.getSource() == pictureButton) setPhoto(picture);
		else if (e.getSource() == friendField || e.getSource() == friendButton) addFriend(friend);	
	}
    
   
    
    
	private void addProfile(String name) {

    		
    		profile = new FacePamphletProfile(name);
   
			if (database.containsProfile(name))  {
    			currentProfile = profile;
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("A profile with the name " + currentProfile.getName() + " already exists");
    			
    		} else {
    			database.addProfile(profile);
    			currentProfile = profile;
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("New profile added");
    		}
			
	}

	private void deleteProfile(String name) {

		if (database.containsProfile(name))  {

			Iterator<String> it = database.getProfile(name).getFriends();
			while(it.hasNext()) {
				String friendToDeleteFrom = it.next();
				database.getProfile(friendToDeleteFrom).removeFriend(name);
			}
			
			database.deleteProfile(name);
			currentProfile = null;
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Profile of " + name + " deleted");
			
		} else {
			currentProfile = null;
			canvas.displayProfile(currentProfile);
			canvas.showMessage("A profile with the name " + name + " does not exist");	
		}
	}

    private void lookupProfile(String name) {
    	//println("Lookup:  " + name);
		if (database.containsProfile(name))  {
			currentProfile = database.getProfile(name);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Displaying " + currentProfile.getName());
		} else {
			currentProfile = null;
			canvas.displayProfile(currentProfile);
			canvas.showMessage("A profile with the name " + name + " does not exist");
		}
    }

    private void setStatus(String status) {
    	if (currentProfile == null) {
			canvas.showMessage("Please select a profile to change status");
		}
		else {
			currentProfile.setStatus(status);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Status updated to" + currentProfile.getStatus());
		}
    }

    private void setPhoto(String picture) {
    	canvas.displayProfile(currentProfile);
		if (currentProfile == null) {
			canvas.showMessage("Please select a profile to change picture");
		}
		else {
			GImage image = null;
	        try {
	            image = new GImage(picture);
	            currentProfile.setImage(image);
    	        canvas.displayProfile(currentProfile);
    			canvas.showMessage("Picture updated");

	            
	        } catch (ErrorException ex) {
    			canvas.showMessage("Unable to open image file: " + pictureField.getText());
	        }   
		} 
    }

    private void addFriend(String friend) {
    	if (currentProfile == null) {
			canvas.showMessage("Please select a profile to add friend");
		}
		else if (database.containsProfile(friend)) {
			
			if(currentProfile.addFriend(friend)) {
				database.getProfile(friend).addFriend(currentProfile.getName());
				canvas.displayProfile(currentProfile);
				canvas.showMessage(friend + " added as friend");
			} else {
				canvas.showMessage(currentProfile.getName() + " already has " + friend + " as a friend");
			}
			    		
		} else {
			canvas.showMessage(friend + " does not exist");
		}
    }

    private void displayCurrentProfile() {
    	if (currentProfile == null) println("--> No Current Profile.");
    	else println("--> Current profile: " + currentProfile.toString());
    	
    }
    
    
    
    /*	Private instance variables */
    private FacePamphletProfile profile;
    private FacePamphletProfile currentProfile = new FacePamphletProfile(null);
    private FacePamphletDatabase database = new FacePamphletDatabase();
    private FacePamphletCanvas canvas;
    
	private JTextField nameField;
	private JButton addButton;
	private JButton deleteButton;
	private JButton lookupButton;
	private JTextField statusField;
	private JButton statusButton;
	private JTextField pictureField;
	private JButton pictureButton;
	private JTextField friendField;
	private JButton friendButton;
	
}
