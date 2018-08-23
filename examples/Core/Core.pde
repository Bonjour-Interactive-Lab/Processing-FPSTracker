import fpstracker.core.*;


CustomTracker trackerInt, trackerFloat;
FPSTracker fpst;
MillisTracker mt;
MemoryTracker mbt;

int ixd;
void setup() {
  size(400, 400);
  smooth();

  trackerInt = new CustomTracker(this, 10);
  trackerFloat = new CustomTracker(this, 10);
  fpst = new FPSTracker(this, 10);
  mt = new MillisTracker(this, 10);
  mbt = new MemoryTracker(this, 10);
  frameRate(60);
}

void draw() {

  background(204);
  int vInt = round(random(30, 100));
  float vFloat= random(30, 100);

  trackerInt.addSample(vInt);
  trackerFloat.addSample(vFloat);

  int minInt = (int) trackerInt.getMinSample();
  int maxInt = (int) trackerInt.getMaxSample();

  float minFloat = (float) trackerFloat.getMinSample();
  float maxFloat = (float) trackerFloat.getMaxSample();

   println("--");
   println("min: "+minInt+"\tmax: "+maxInt);
   printArray(trackerInt.getSampleList());
   println("\nmin: "+minFloat+"\tmax: "+maxFloat);
   printArray(trackerFloat.getSampleList());
   
  fpst.addSample();
  float minfps = (float) fpst.getMinSample();
  float maxfps = (float) fpst.getMaxSample();
  println("\nmin: "+minfps+"\tmax: "+maxfps);
  printArray(fpst.getSampleList());
  println(fpst.getSampleList().get(fpst.getSampleList().size()-1));
  
  
  mt.addSample();
  float minmillis = (float) mt.getMinSample();
  float maxmillis = (float) mt.getMaxSample();
  println("\nmin: "+minmillis+"\tmax: "+maxmillis);
  printArray(mt.getSampleList());
  println(mt.getSampleList().get(mt.getSampleList().size()-1));
  
  mbt.addSample();
  int minmemory = (int) mbt.getMinSample();
  int maxmemory = (int) mbt.getMaxSample();
  println("\nmin: "+minmemory+"\tmax: "+maxmemory);
  printArray(mbt.getSampleList());
  println(mbt.getSampleList().get(mbt.getSampleList().size()-1));
  println("free: "+mbt.getFreeMemory()+"\n"+
          "Max: "+mbt.getMaxMemorySize()+"\n"+
          "Total: "+mbt.getTotalMemory());
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
  }
}
