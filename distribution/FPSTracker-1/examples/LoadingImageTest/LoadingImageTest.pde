/**
* this example show performances tracking on a program loading random image at draw
*/
import fpstracker.core.*;

PerfTracker pt;

void settings() {
  size(960, 540);
}

void setup() {
  pt = new PerfTracker(this, 100);
}

void draw() {
  background(204);
  
  int index = (int) random(0, 7);
  PImage test = loadImage(index+".png");
  
  imageMode(CENTER);
  image(test, width/2, height/2, width * 0.75, height * 0.75);
  
  pt.displayOnTopBar("Test program");
  pt.display(0, 0);
}

void keyPressed() {

  if (key == 'p' || key == 'P') {
    pt.playpause();
  }
  
  if (key == 'r' || key == 'R') {
    pt.resetSamples();
  }
}
