/**
* This example show how to use the PerfTracker object 
* in order to automatically track FPS, Millis and Memory of your program.
* Click on the PerfTracker object to change the tracking display (FPS, MS, MB)
*/

import fpstracker.core.*;

PerfTracker pt;
boolean showAll;

int ixd;
void settings() {
  size(400, int(60 * 3));
}

void setup() {
  //instanciate PerfTracker object public PerfTracker(PApplet context, int samplingSize)
  pt = new PerfTracker(this, 100);
  
  //pt.setUIComputation(false);
  //you can disable the UI Pannel drawing if you want to only use data as string
  
  println(pt.getLibraryInfos());
}

void draw() {
  background(204);
  
  //get data sa string
  String perfData = pt.toString();
  fill(20);
  text(perfData, 150, 20, 200, height);
  
  //Display the tracked data into the top bar of the program
  pt.displayOnTopBar("My Program");

  if(!showAll){
    pt.display(0, 0); //display the actual tracker (default is FPS)
  }else{
    pt.displayAll(0,0); //display all tracker into one column
  }
}

void keyPressed() {
  if (key == 's') {
    //change the number of data you what to keep into your sampling history
    if (ixd%2 == 0) {
      pt.setSamplingSize(3);
    } else {
      pt.setSamplingSize(100);
    }
    ixd++;
  }

  if (key == 'p' || key == 'P') {
    //play/pause the tracking
    pt.playpause();
  }

  if (key == 'a') {
    //display the next pannel (you can clik on the pannel to change the panel to the next)
    pt.displayNextPannel();
  }

  if (key == 'z') {
    //display the previous pannel (you can clik on the pannel to change the panel to the next)
    pt.displayPreviousPannel();
  }
  
  if(key == 'q'){
    showAll = !showAll;
  }
  
  if(key == 'r'){
    //empty all the data and reset the trackers
    pt.resetSamples();
  }
}
