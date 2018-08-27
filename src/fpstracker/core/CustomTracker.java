package fpstracker.core;

import fpstracker.ui.DesignUI;
import fpstracker.ui.UIPanel;
import processing.core.PApplet;

public class CustomTracker extends BaseCustomTracker implements AddSample{
	
	public CustomTracker(PApplet parent, int samplingSize) {
		super(parent);
		this.sampler = new Sampler(this.parent, samplingSize, TrackerType.CUSTOM);
		this.ui = new UIPanel(this.parent, 200, 100, DesignUI.CUSTOM.getUI());
	}
	
	public CustomTracker(PApplet parent, int samplingSize, int width, int height) {
		super(parent);
		this.sampler = new Sampler(this.parent, samplingSize, TrackerType.CUSTOM);
		this.ui = new UIPanel(this.parent, width, height, DesignUI.CUSTOM.getUI());
	}
	
	public void addSample(int sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
	}
	
	public void addSample(float sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
	}
	

	public void addSample(long sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
	}

	public void addSample(short sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
		
	}

	public void addSample(double sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
	}

	public void addSample(byte sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
	}
}
