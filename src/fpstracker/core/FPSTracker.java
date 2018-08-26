package fpstracker.core;

import processing.core.PApplet;

public class FPSTracker extends BaseCustomTracker{
	private int startSmooth = 20;
	private int smoothLoop = 0;
	private int frameRate = 10;

	public FPSTracker(PApplet parent, int samplingSize) {
		super(parent);
		this.sampler = new Sampler(this.parent, samplingSize, TrackerType.FPS);
		this.lastSample = (long) 0;
	}

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
			
			//double rate = 1000000.0 / ((sample - (long)this.lastSample) / 1000000.0);
			//float instantaneousRate = (float) (rate / 1000.0);
			//frameRate = (int) Math.round((frameRate * 0.9f) + (instantaneousRate * 0.1f));
				
			//Using P5.frameRate
			frameRate = (int) Math.round(this.parent.frameRate);
			this.sampler.addSample(frameRate);
			this.lastSample = (long) sample;
		}
	}

	

}