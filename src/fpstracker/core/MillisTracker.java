package fpstracker.core;

import processing.core.PApplet;

public class MillisTracker extends BaseCustomTracker{
	protected float millis = 0;

	public MillisTracker(PApplet parent, int samplingSize) {
		super(parent);
		this.sampler = new Sampler(this.parent, samplingSize, TrackerType.FPS);
		this.lastSample = (long) 0;
	}
	
	public void addSample() {
		this.addSample(System.nanoTime());
	}


	private void addSample(long sample) {
		if(this.sampler.isPlaying()) {
			millis = (float)((sample - (long)this.lastSample) / 1000000.0);
			this.sampler.addSample(millis);
			this.lastSample = (long) sample;
		}
	}
}
