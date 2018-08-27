import fpstracker.core.*;

PerfTracker pt;
boolean showAll;

int ixd;
float s = 1;
void settings() {
  size(int(400 * s), int(400 * s));
}

void setup() {
  //smooth();
  pt = new PerfTracker(this, 100);
  //pt.setUIComputation(false);
}

void draw() {
  background(204);
  pt.displayOnTopBar();

  if(!showAll){
    pt.display(0, 0);
  }else{
    pt.displayAll(0,0);
  }
}

void keyPressed() {
  if (key == 's') {
    if (ixd%2 == 0) {
      pt.setSamplingSize(3);
    } else {
      pt.setSamplingSize(100);
    }
    println(pt.getSamplingSize());
    ixd++;
  }

  if (key == 'p' || key == 'P') {
    pt.playpause();
  }

  if (key == 'a') {
    pt.displayNextPannel();
  }

  if (key == 'z') {
    pt.displayPreviousPannel();
  }
  
  if(key == 'q'){
    showAll = !showAll;
  }
}
