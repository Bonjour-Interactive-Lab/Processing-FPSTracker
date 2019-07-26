/**
 * This example show how to use the PerfTracker object 
 * in order to automatically track FPS, Millis and Memory of your program.
 * Click on the PerfTracker object to change the tracking display (FPS, MS, MB)
 */

import fpstracker.core.*;
import peasy.*;

PeasyCam cam;
PerfTracker pt;
boolean showAll;

int ixd;
void settings() {
  size(400, 400, P3D);
}

void setup() {
  //instanciate PerfTracker object public PerfTracker(PApplet context, int samplingSize)
  pt = new PerfTracker(this, 100);
  cam = new PeasyCam(this, 400);

  //pt.setUIComputation(false);
  //you can disable the UI Pannel drawing if you want to only use data as string

  println(pt.getLibraryInfos());
}

void draw() {
  background(204);

  rotateX(frameCount * 0.01);
  rotateY(frameCount * 0.0125);
  box(100);


  //Display the tracked data into the top bar of the program
  pt.displayOnTopBar("My Program");

  //This is a hack. The actual display methods seems to create a bug with peasy cam on the main context. 
  PGraphics trackers = createGraphics(pt.getActualPannel().width, pt.getActualPannel().height);
  trackers.beginDraw();
  trackers.image(pt.getActualPannel(), 0, 0);
  trackers.endDraw();

  try {
    cam.beginHUD();
    if (!showAll) {
      //pt.display(0, 0); //display the actual tracker (default is FPS)
    } else {
      //pt.displayAll(0,0); //display all tracker into one column
    }
    //pt.display(0, 0);
    g.image(trackers, 0, 0);
    cam.endHUD();
  }
  catch(Exception e) {
    e.printStackTrace();
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

  if (key == 'q') {
    showAll = !showAll;
  }

  if (key == 'r') {
    //empty all the data and reset the trackers
    pt.resetSamples();
  }
}
