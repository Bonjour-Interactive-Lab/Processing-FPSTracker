package fpstracker.core;

import fpstracker.ui.DesignUI;
import fpstracker.ui.UIPanel;
import processing.core.PApplet;

public class MillisTracker extends BaseCustomTracker{
	private int startSmooth = 10;
	private int smoothLoop = 0;
	protected int millis = 0;

	public MillisTracker(PApplet parent, int samplingSize) {
		super(parent);
		this.sampler = new Sampler(this.parent, samplingSize, TrackerType.MILLIS);
		this.lastSample = (long) System.nanoTime();
		this.ui = new UIPanel(this.parent, 110, 60, DesignUI.MILLIS.getUI());
	}
	
	public MillisTracker(PApplet parent, int samplingSize, int width, int height) {
		super(parent);
		this.sampler = new Sampler(this.parent, samplingSize, TrackerType.MILLIS);
		this.lastSample = (long) System.nanoTime();
		this.ui = new UIPanel(this.parent, width, height, DesignUI.MILLIS.getUI());
	}

	@Override
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
	
	@Override
	public void computePanel() {
		// TODO Auto-generated method stub
		this.ui.computePannel(this.toStringMinify(), this.getSampleList(), 0.0, this.getMaxSample());
	}
}
