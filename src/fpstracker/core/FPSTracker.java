package fpstracker.core;

import fpstracker.ui.DesignUI;
import fpstracker.ui.UIPanel;
import processing.core.PApplet;

public class FPSTracker extends BaseCustomTracker{
	private int startSmooth = 20;
	private int smoothLoop = 0;
	private int frameRate = 10;

	public FPSTracker(PApplet parent, int samplingSize) {
		super(parent);
		this.sampler = new Sampler(this.parent, samplingSize, TrackerType.FPS);
		this.lastSample = (long) 0;
		this.ui = new UIPanel(this.parent, 110, 60, DesignUI.FPS.getUI());
	}
	
	public FPSTracker(PApplet parent, int samplingSize, int width, int height) {
		super(parent);
		this.sampler = new Sampler(this.parent, samplingSize, TrackerType.FPS);
		this.lastSample = (long) 0;
		this.ui = new UIPanel(this.parent, width, height, DesignUI.FPS.getUI());
	}
	
	@Override
	public void addSample() {
		this.addSample(System.nanoTime());
	}

	private void addSample(long sample) {
		if(this.sampler.isPlaying()) {
			// avoid to smooth the first 20 values
			if(smoothLoop < startSmooth) {
				smoothLoop ++;
				this.sampler.minSample = null;
			}
			
			double rate = 1000000.0 / ((sample - (long)this.lastSample) / 1000000.0);
			double instantaneousRate = (rate / 1000.0);
			frameRate = (int) Math.round((frameRate * 0.9f) + (instantaneousRate * 0.1f));
				
			//Using P5.frameRate
			//frameRate = (int) Math.round(this.parent.frameRate);
			this.sampler.addSample(frameRate);
			this.lastSample = (long) sample;
		}
	}
	
	@Override
	public void computePanel() {
		// TODO Auto-generated method stub
		this.ui.computePannel(this.toStringMinify(), this.getSampleList(), 0.0f, this.getMaxSample());
	}

}
