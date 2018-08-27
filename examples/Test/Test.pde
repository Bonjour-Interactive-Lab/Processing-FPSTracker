import fpstracker.core.*;

PerfTracker pt;

void settings() {
  size(1280, 720);
}

void setup() {
  //smooth();
  pt = new PerfTracker(this, 100);
}

void draw() {
  background(204);
  
  int index = (int) random(0, 7);
  PImage test = loadImage(index+".png");
  imageMode(CENTER);
  image(test, width/2, height/2, width/2, height/2);
  
  pt.displayOnTopBar("Test program");
  pt.display(0, 0);
}

void keyPressed() {

  if (key == 'p' || key == 'P') {
    pt.playpause();
  }
}
