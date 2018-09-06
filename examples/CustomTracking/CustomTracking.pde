/**
 * This example show how to create and use fps, millis, memory and custom nono-automatic tracker.
 * use keys :
 * - a : display previous pannel on the PerfTracker
 * - z : display next pannel on the PerfTracker
 * - p : pause trackers
 * - s : reset tracker (empty all samples)
 */
import fpstracker.core.*;
import fpstracker.ui.*;


CustomTracker trackerInt, trackerFloat;
FPSTracker fpst;
MillisTracker mt;
MemoryTracker mbt;
PerfTracker pt;


float s = 1;
void settings() {
  size(int(400 * s), int(200 * s));
}

void setup() {

  //instanciate trackers
  trackerInt = new CustomTracker(this, 100);
  trackerFloat = new CustomTracker(this, 100);
  fpst = new FPSTracker(this, 100);
  mt = new MillisTracker(this, 100);
  mbt = new MemoryTracker(this, 100);
  pt = new PerfTracker(this, 100);

  //resize the pannel of a tracker (usefull if you need to display float/double data)
  trackerFloat.setSize(150, 100);

  //define a custom Design UI defined as : 
  /**public UI(int backgroundRed, int backgroundGreen, int backgroundBlue, 
			int colorRed, int colorGreen, int colorBlue, 
			int graphBackgroundRed, int graphBackgroundGreen, int graphBackgroundRBlue, 
			int graphColorRed, int graphColorGreen, int graphColorBlue,
			float fontSize, float margin)
	*/
  trackerInt.setDesignUI(new UI(
    20, 20, 20, 
    200, 200, 200, 
    40, 40, 40, 
    240, 240, 240, 
    10, 2));
}

void draw() {
  int vInt = round(random(30, 100));
  float vFloat= random(30, 100);

  //add sample to custom trackers
  trackerInt.addSample(vInt);
  trackerFloat.addSample(vFloat);

  //add sample to fps, millis and memory tracker (keeps in mind memory tracker will track runtime memory using multiple memory trackers will give the same results)
  fpst.addSample();
  mt.addSample();
  mbt.addSample();

  //get the min/max sample of each trackers
  int minInt = (int) trackerInt.getMinSample();
  int maxInt = (int) trackerInt.getMaxSample();

  float minFloat = (float) trackerFloat.getMinSample();
  float maxFloat = (float) trackerFloat.getMaxSample();

  int minmillis = (int) mt.getMinSample();
  int maxmillis = (int) mt.getMaxSample();

  int minfps = (int) fpst.getMinSample();
  int maxfps = (int) fpst.getMaxSample();

  int minmemory = (int) mbt.getMinSample();
  int maxmemory = (int) mbt.getMaxSample();

  println("--");
  println("CustomTracker (int)");
  println("min: "+minInt+"\tmax: "+maxInt);
  //printArray(trackerInt.getSampleList());

  println("\nCustomTracker (float)");
  println("min: "+minFloat+"\tmax: "+maxFloat);
  // printArray(trackerFloat.getSampleList());

  println("\nFPSTracker");
  println("min: "+minfps+"\tmax: "+maxfps);
  //printArray(fpst.getSampleList());
  println(fpst.getSampleList().get(fpst.getSampleList().size()-1));

  println("\nMillisTracker");
  println("min: "+minmillis+"\tmax: "+maxmillis);
  // printArray(mt.getSampleList());
  println(mt.getSampleList().get(mt.getSampleList().size()-1));

  println("\nMemoryTracker");
  println("min: "+minmemory+"\tmax: "+maxmemory);
  // printArray(mbt.getSampleList());
  println(mbt.getSampleList().get(mbt.getSampleList().size()-1));
  println("free: "+mbt.getFreeMemory()+"\n"+
    "Max: "+mbt.getMaxMemorySize()+"\n"+
    "Total: "+mbt.getTotalMemory());

  //get values as string
  String headerFPS = fpst.toString();
  String headerMemory = mbt.toString();
  String headerMemoryMinified = mbt.toStringMinify(); //return a minified string value
  String headerMillis = mt.toString();
  String headerCustom = trackerFloat.toString();


  surface.setTitle(headerFPS);

  background(204);

  //compute UI panel for each trackers
  fpst.computePanel();
  mt.computePanel();
  mbt.computePanel();
  trackerFloat.computePanel();
  trackerInt.computePanel();

  //display panel of each trackers
  fpst.displayPanel(0, 0);
  mbt.displayPanel(0, 60);
  mt.displayPanel(0, 60*2);
  trackerFloat.displayPanel(110, 0);
  trackerInt.displayPanel(110, 100);

  pt.display(width-pt.getWidth(), 0);
  //pt.displayAll(width-pt.getWidth(), 0); //display all trackers of the PerfTracker (FPS, MILLIS, MEMORY) into one column

  textAlign(RIGHT);
  fill(20);
  text(frameRate, width-10, height - 10);
}

void keyPressed() {

  if (key == 'p' || key == 'P') {
  	//play/pause a tracker
    if (trackerInt.isPlaying()) {
      trackerInt.pause();
    } else {
      trackerInt.play();
    }

    trackerFloat.playpause();
    fpst.playpause();
    mt.playpause();
    mbt.playpause();

  }

  if (key == 's' || key == 'S') {
  	//reset a sample
    trackerFloat.resetSamples();
    fpst.resetSamples();
    mt.resetSamples();
    mbt.resetSamples();
    pt.resetSamples();
  }

  if (key == 'a') {
  	//display previous pannel of the PerfTracker
    pt.displayNextPannel();
  }

  if (key == 'z') {
  	//display next pannel of the PerfTracker
    pt.displayPreviousPannel();
  }
}
