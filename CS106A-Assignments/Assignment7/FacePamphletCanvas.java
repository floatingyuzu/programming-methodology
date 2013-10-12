/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;

import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
				
		if (message != null) remove(message);
		
		message = new GLabel(msg, (getWidth() - msg.length()) / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
		message.setFont(MESSAGE_FONT);
		add(message);
		
	}
	
	


	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		
		removeAll();
		
		if(profile != null) {
			showName(profile);
			showPicture(profile);
			showStatus(profile);
			showFriends(profile);
		}
	}
	
	private void showName(FacePamphletProfile profile) {
		nameText = new GLabel(profile.getName());
		nameText.setLocation(LEFT_MARGIN, TOP_MARGIN + nameText.getAscent()); 
		nameText.setColor(Color.BLUE);
		nameText.setFont(PROFILE_IMAGE_FONT);
		add(nameText);
		
	}
	
	private void showPicture(FacePamphletProfile profile) {
		
		if (profile.getImage() == null) showBlankImage();
		else {
			GImage picture = profile.getImage(); 
			picture.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			picture.setLocation(LEFT_MARGIN, IMAGE_MARGIN + nameText.getAscent());
			add(picture);
		}
		
	}
	
	private void showBlankImage() {
		rect = new GRect(LEFT_MARGIN, IMAGE_MARGIN + nameText.getAscent(), IMAGE_WIDTH, IMAGE_HEIGHT);
		GLabel noImage = new GLabel("No Image");
		noImage.setLocation(((rect.getWidth() - noImage.getWidth() - LEFT_MARGIN) / 2), (rect.getHeight() - noImage.getAscent()) / 2 + IMAGE_MARGIN + nameText.getAscent());
		noImage.setFont(PROFILE_IMAGE_FONT);
		add(rect);
		add(noImage);
	}
	
	private void showStatus(FacePamphletProfile profile) {
		String status = profile.getStatus();
		statusText = new GLabel(profile.getName() + " is " + status);
		statusText.setFont(PROFILE_STATUS_FONT);
		statusText.setLocation(LEFT_MARGIN, IMAGE_MARGIN + nameText.getAscent() + rect.getHeight() + STATUS_MARGIN);
		add(statusText);
	}
	
	private void showFriends(FacePamphletProfile profile) { 
		friends = new GLabel("Friends:");
		friends.setLocation((getWidth() - friends.getWidth()) / 2, IMAGE_MARGIN + nameText.getAscent());
		friends.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friends);
		
		String listOfFriends;
		int counter = 1;
		Iterator<String> it = profile.getFriends();
		while(it.hasNext()) {
			listOfFriends = it.next();
			GLabel currentFriend = new GLabel(listOfFriends);
			currentFriend.setLocation((getWidth() - friends.getWidth()) / 2, IMAGE_MARGIN + nameText.getAscent() + (friends.getAscent() * counter));
			currentFriend.setFont(PROFILE_FRIEND_FONT);
			add(currentFriend);
			counter++;
			
		}
		
		
	}

	/* Private Instance Variables */
	private GLabel message = null;
	private GLabel friends;
	private GLabel nameText;
	private GLabel statusText;
	private GRect rect;
	
}
