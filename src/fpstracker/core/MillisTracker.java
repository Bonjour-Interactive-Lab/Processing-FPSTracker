package fpstracker.core;

import processing.core.PApplet;

public class MillisTracker extends BaseCustomTracker{
	private int startSmooth = 10;
	private int smoothLoop = 0;
	protected int millis = 0;

	public MillisTracker(PApplet parent, int samplingSize) {
		super(parent);
		this.sampler = new Sampler(this.parent, samplingSize, TrackerType.MILLIS);
		this.lastSample = (long) System.nanoTime();
	}
	
	public void addSample() {
		this.addSample(System.nanoTime());
	}

	private void addSample(long sample) {
		if(this.sampler.isPlaying()) {
			if(smoothLoop < startSmooth) {
				smoothLoop ++;
				this.sampler.minSample = null;
				this.sampler.maxSample = null;
			}
			millis = (int)((sample - (long)this.lastSample) / 1000000.0);
			this.sampler.addSample(millis);
			
			this.lastSample = (long) sample;
		}
	}
	
	@Override public void play() {
		super.play();
		this.lastSample = System.nanoTime();
	} 
}
