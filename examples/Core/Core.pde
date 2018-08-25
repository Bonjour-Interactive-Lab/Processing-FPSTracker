import fpstracker.core.*;


CustomTracker trackerInt, trackerFloat;
FPSTracker fpst;
MillisTracker mt;
MemoryTracker mbt;
Design design;

int ixd;
float s = 1;
void settings() {
  size(int(400 * s), int(300 * s));
}

void setup() {
  //smooth();

  trackerInt = new CustomTracker(this, 60);
  trackerFloat = new CustomTracker(this, 60);
  fpst = new FPSTracker(this, 60);
  mt = new MillisTracker(this, 60);
  mbt = new MemoryTracker(this, 60);
  //frameRate(30);

  initPanel(200, 100);

}

void draw() {
  int vInt = round(random(30, 100));
  float vFloat= random(30, 100);

  //frameRate(noise(frameCount * 0.25) * 120);

  trackerInt.addSample(vInt);
  trackerFloat.addSample(vFloat);
  fpst.addSample();
  mt.addSample();
  mbt.addSample();
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
  /*
  println("--");
   println("CustomTracker (int)");
   println("min: "+minInt+"\tmax: "+maxInt);
   printArray(trackerInt.getSampleList());
   
   println("\nCustomTracker (float)");
   println("min: "+minFloat+"\tmax: "+maxFloat);
   printArray(trackerFloat.getSampleList());
   
   println("\nFPSTracker");
   println("min: "+minfps+"\tmax: "+maxfps);
   printArray(fpst.getSampleList());
   println(fpst.getSampleList().get(fpst.getSampleList().size()-1));
   
   println("\nMillisTracker");
   println("min: "+minmillis+"\tmax: "+maxmillis);
   printArray(mt.getSampleList());
   println(mt.getSampleList().get(mt.getSampleList().size()-1));
   
   println("\nMemoryTracker");
   println("min: "+minmemory+"\tmax: "+maxmemory);
   printArray(mbt.getSampleList());
   println(mbt.getSampleList().get(mbt.getSampleList().size()-1));
   println("free: "+mbt.getFreeMemory()+"\n"+
   "Max: "+mbt.getMaxMemorySize()+"\n"+
   "Total: "+mbt.getTotalMemory());
   
   */

  String headerFPS = fpst.toString();
  String headerMemory = mbt.toStringMinify();//mbt.getSampleList().get(mbt.getSampleList().size()-1) + " Memory ["+minmemory+"-"+maxmemory+"]";
  String headerMillis = mt.toString();//mt.getSampleList().get(mt.getSampleList().size()-1) + " Millis ["+minmillis+"-"+maxmillis+"]";
  String headerCustom = trackerFloat.toString();//trackerFloat.getSampleList().get(trackerFloat.getSampleList().size()-1) + " Custom ["+minFloat+"-"+maxFloat+"]";

  String topBar = this.toString() +" - "+ headerFPS + " - "+ headerMillis +" - "+ mbt.toString();

  //scale(s);

  surface.setTitle(topBar);
  background(204);

  //0 for fps
  computePanel(headerFPS, fpst.getSampleList(), 0.0, fpst.getMaxSample(), Design.FPS);
  image(buffer, 0, 0);
  computePanel(headerMemory, mbt.getSampleList(), mbt.getMinSample(), mbt.getMaxSample(), Design.Memory);

  image(buffer, 0, buffer.height);
  computePanel(headerMemory, mbt.getSampleList(), mbt.getMinSample(), mbt.getMaxSample(), Design.Memory);

  computePanel(headerMillis, mt.getSampleList(), 0.0, mt.getMaxSample(), Design.Millis);
  image(buffer, 0, buffer.height * 2);


  computePanel(headerCustom, trackerFloat.getSampleList(), trackerFloat.getMinSample(), trackerFloat.getMaxSample(), Design.Custom);
  image(buffer, buffer.width, 0);

  textAlign(RIGHT);
  fill(20);
  text(frameRate, width, height - 20);

  textAlign(TOP, CENTER);
  float margin = 20;
  float x = buffer.width + margin;
  float y = buffer.height + margin;
  text(topBar, x, y, width - x, height -y);


  //if (frameCount > 5)
  //noLoop();
}

void keyPressed() {
  if (key != 'p' && key != 'P') {
    if (ixd%2 == 0) {
      trackerInt.setSamplingSize(3);
      trackerFloat.setSamplingSize(3);
      fpst.setSamplingSize(3);
      mt.setSamplingSize(3);
    } else {
      trackerInt.setSamplingSize(30);
      trackerFloat.setSamplingSize(30);
      fpst.setSamplingSize(30);
      mt.setSamplingSize(30);
    }
    ixd++;
  }

  if (key == 'p' || key == 'P') {
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
}
