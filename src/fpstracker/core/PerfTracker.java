package fpstracker.core;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.MouseEvent;

public class PerfTracker {
	private PApplet parent;
	private List<BaseCustomTracker> trackerList;
	private int actualPannel;
	private boolean displayAll;
	private boolean computeUI;
	private String datasAsString;

	public PerfTracker(PApplet parent, int samplingSize) {
		this.parent = parent;
		trackerList = new ArrayList<BaseCustomTracker>();
		trackerList.add(new FPSTracker(this.parent, samplingSize));
		trackerList.add(new MillisTracker(this.parent, samplingSize));
		trackerList.add(new MemoryTracker(this.parent, samplingSize));
		this.actualPannel = 0;
		this.displayAll = false;
		this.computeUI = true;
		this.datasAsString = "";

		this.parent.registerMethod("draw", this);
		this.parent.registerMethod("mouseEvent", this);
	}

	public PerfTracker(PApplet parent, int samplingSize, int width, int height) {
		this.parent = parent;
		trackerList = new ArrayList<BaseCustomTracker>();
		trackerList.add(new FPSTracker(this.parent, samplingSize, width, height));
		trackerList.add(new MillisTracker(this.parent, samplingSize, width, height));
		trackerList.add(new MemoryTracker(this.parent, samplingSize, width, height));
		this.actualPannel = 0;
		this.displayAll = false;
		this.computeUI = true;
		this.datasAsString = "";

		this.parent.registerMethod("draw", this);
		this.parent.registerMethod("mouseEvent", this);
	}

	//Automatic methods set for processing
	public void draw() {
		this.addSample();
		long uiStartTime = System.nanoTime();
		if(this.computeUI) {
			if(!this.displayAll) {
				computePanel();
			}else {
				computePanels();
			}
		}
		this.computeDataAsString();
		long uiEndTime = System.nanoTime() - uiStartTime;
		this.trackerList.get(0).lastSample = (long) this.trackerList.get(0).lastSample - (long) uiEndTime;
		this.trackerList.get(1).lastSample = (long) this.trackerList.get(1).lastSample - (long) uiEndTime;
		//System.out.println(te);
	}
	
	private void computeDataAsString() {
		this.datasAsString = "";
		for(BaseCustomTracker tracker : trackerList) {
			if(tracker.isPlaying()) {
				this.datasAsString += tracker.toString()+" - ";
			}
		}
	}


	public void mouseEvent(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		if(computeUI && !this.displayAll) {
			if(event.getAction() == MouseEvent.PRESS) {
				if(x >= this.trackerList.get(0).getXY()[0] &&
						x <= this.trackerList.get(0).getXY()[0] + this.getWidth() &&
						y >= this.trackerList.get(0).getXY()[1] &&
						y <= this.trackerList.get(0).getXY()[1] + this.getHeight()) {
					this.displayNextPannel();
				}
			}
		}
	}


	public void displayOnTopBar() {
		this.displayOnTopBar(this.parent.toString());
	}

	public void displayOnTopBar(String programName) {
		this.parent.getSurface().setTitle(programName + ": "+this.datasAsString);
	}

	public void display(int x, int y) {
		this.display(this.actualPannel, this.parent.g, x, y);
	}

	public void display(PGraphics context, int x, int y) {
		this.display(this.actualPannel, this.parent.g, x, y);
	}

	public void display(int pannel, int x, int y) {
		this.display(pannel, this.parent.g, x, y);
	}

	public void display(int pannel, PGraphics context, int x, int y) {
		try {
			this.displayUI(pannel, context, x, y);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void displayUI(int pannel, PGraphics context, int x, int y) throws Exception{
		if(this.computeUI) {
			this.displayAll = false;
			this.trackerList.get(pannel).displayPanel(context, x, y);
		}else {
			throw new Exception("The drawing of the UI as been disabled. Please reactive it");
		}
	}

	public void displayAll(int x, int y) {
		this.displayAll(this.parent.g, x, y);
	}

	public void displayAll(PGraphics context, int x, int y) {
		try {
			this.displayAllUI(context, x, y);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void displayAllUI(PGraphics context, int x, int y) throws Exception{
		if(this.computeUI) {
			this.displayAll = true;
			for(BaseCustomTracker tracker : trackerList) {
				tracker.displayPanel(context, x, y);
				y += tracker.getPanel().height;
			}
		}else {
			throw new Exception("The drawing of the UI as been disabled. Please reactive it");
		}
	}

	public void displayNextPannel() {
		if(!this.displayAll) {
			if(this.actualPannel < this.trackerList.size()-1) {
				this.actualPannel ++;
			}else {
				this.actualPannel = 0;
			}
		}
	}

	public void displayPreviousPannel() {
		if(!this.displayAll) {
			if(this.actualPannel > 0) {
				this.actualPannel --;
			}else {
				this.actualPannel =  this.trackerList.size() - 1;
			}
		}
	}

	//Tracker system
	private void addSample() {
		for(BaseCustomTracker tracker : trackerList) {
			tracker.addSample();
		}
	}

	private void computePanel() {
		trackerList.get(this.actualPannel).computePanel();
	}

	private void computePanels() {
		for(BaseCustomTracker tracker : trackerList) {
			tracker.computePanel();
		}
	}

	public void play() {
		for(BaseCustomTracker tracker : trackerList) {
			tracker.play();
		}
	}

	public void play(TrackerType type) {
		for(BaseCustomTracker tracker : trackerList) {
			if(type == tracker.getType()) {
				tracker.play();
			}
		}
	}

	public void pause() {
		for(BaseCustomTracker tracker : trackerList) {
			tracker.pause();
		}
	}

	public void pause(TrackerType type) {
		for(BaseCustomTracker tracker : trackerList) {
			if(type == tracker.getType()) {
				tracker.pause();
			}
		}
	}

	public void playpause() {
		for(BaseCustomTracker tracker : trackerList) {
			tracker.playpause();
		}
	}

	public void playpause(TrackerType type) {
		for(BaseCustomTracker tracker : trackerList) {
			if(type == tracker.getType()) {
				tracker.playpause();
			}
		}
	}

	public void setSamplingSize(int samplingSize) {
		for(BaseCustomTracker tracker : trackerList) {
			tracker.setSamplingSize(samplingSize);
		}
	}

	public void setWidth(int width) {
		for(BaseCustomTracker tracker : trackerList) {
			tracker.setWidth(width);
		}
	}

	public void setHeight(int height) {
		for(BaseCustomTracker tracker : trackerList) {
			tracker.setHeight(height);
		}
	}

	public void setSize(int width, int height) {
		for(BaseCustomTracker tracker : trackerList) {
			tracker.setSize(width, height);
		}
	}

	public void setUIComputation(boolean state) {
		this.computeUI = state;
	}

	public int getSamplingSize() {
		return trackerList.get(0).getSamplingSize();
	}

	public List<Number> getSampleList(TrackerType type) {
		List<Number> list = null;
		for(BaseCustomTracker tracker : trackerList) {
			if(type == tracker.getType()) {
				list = tracker.getSampleList();
				break;
			}
		}
		return list;
	}

	public Number getMinSample(TrackerType type) {
		Number value = null;
		for(BaseCustomTracker tracker : trackerList) {
			if(type == tracker.getType()) {
				value = tracker.getMinSample();
				break;
			}
		}
		return value;
	}

	public Number getMaxSample(TrackerType type) {
		Number value = null;
		for(BaseCustomTracker tracker : trackerList) {
			if(type == tracker.getType()) {
				value = tracker.getMaxSample();
				break;
			}
		}
		return value;
	}

	public int getMaxMemorySize() {
		return ((MemoryTracker)this.trackerList.get(2)).getMaxMemorySize();
	}

	public int getTotalMemory() {
		return ((MemoryTracker)this.trackerList.get(2)).getTotalMemory();
	}

	public int getFreeMemory() {
		return ((MemoryTracker)this.trackerList.get(2)).getFreeMemory();
	}

	public int getHeight() {
		return this.trackerList.get(this.actualPannel).getPanel().height;
	}

	public int getWidth() {
		return this.trackerList.get(this.actualPannel).getPanel().width;
	}

	public String toString(){
		return this.datasAsString;
	}
}
