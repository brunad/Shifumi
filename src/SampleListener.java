/******************************************************************************\
* Copyright (C) 2012-2013 Leap Motion, Inc. All rights reserved.               *
* Leap Motion proprietary and confidential. Not for distribution.              *
* Use subject to the terms of the Leap Motion SDK Agreement available at       *
* https://developer.leapmotion.com/sdk_agreement, or another agreement         *
* between Leap Motion and you, your company or other organization.             *
\******************************************************************************/

import com.leapmotion.leap.*;

class SampleListener extends Listener {
	
	private boolean position;
	private int count;
	private int fingers;
	private Computer computer;
	private UInterface ui;
	
	private int[] lastFrames={1,2,3,4,5,6,7,8,9,10};
	private int cursor;
	
    public void onInit(Controller controller) {
    	count=0;
    	position=false;
    	fingers=0;
    	cursor=0;
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();

        //Get hands
        for(Hand hand : frame.hands()) {
           
            boolean previous=position;
            if(hand.palmPosition().getY()<200){
            	position=true;
            	if(!previous&&count<3){
            		count++;
            		ui.updateBattle(Integer.toString(count));
            	}
            	//System.out.println("Position: DOWN   count:"+count);
            }
            else{
            	if(count==3) count=0;
            	position=false;
            	//System.out.println("Position: UP   count:"+count);
            }
            

            // Get fingers
            fingers=0;
            for (Finger finger : hand.fingers()) {
            	if(finger.isExtended()){
            		fingers++;
            	}                
            }
            
            if(count==3){
            	
            	int human;
            	lastFrames[cursor++%10]=fingers;
            	if(isStable()){
	            	switch(fingers){
	            		case 2: System.out.println("You: Scissors");human=2;break;
	            		case 0: System.out.println("You: Rock");human=0;break;
	            		case 4: System.out.println("You: Paper");human=1;break;
	            		case 5: System.out.println("You: Paper");human=1;break;
	            		default: System.out.println("You: Unknown...");human=4;break;
	            	}
	            	count=0;
	            	
	            	if(human==4)System.out.println("Sorry, I don't recognize your choice...");
	            	else computer.playComputer(human);
	            	
            	}
            }
            
            
        }

    }
    
    public boolean isStable(){
    	int i;
    	for(i=0;i<9;i++){
    		if(lastFrames[i]!=lastFrames[i+1]) return false;
    	}
    	for(i=0;i<9;i++){
    		lastFrames[i]=i;
    	}
    	return true;
    }
    
    public void setComputer(Computer c){
    	computer=c;
    }
    
    public void setUI(UInterface u){
    	ui=u;
    }
    
}
