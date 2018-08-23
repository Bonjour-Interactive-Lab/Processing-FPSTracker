package fpstracker.core;

import processing.core.PApplet;

public class CustomTracker extends BaseCustomTracker implements AddSample{
	
	public CustomTracker(PApplet parent, int samplingSize) {
		super(parent);
		this.sampler = new Sampler(this.parent, samplingSize, TrackerType.CUSTOM);
	}
	
	@Override
	public void addSample(int sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
	}
	
	@Override
	public void addSample(float sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
	}
	

	@Override
	public void addSample(long sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
	}

	@Override
	public void addSample(short sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
		
	}

	@Override
	public void addSample(double sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
	}

	@Override
	public void addSample(byte sample) {
		// TODO Auto-generated method stub
		if(this.sampler.isPlaying()) {
			this.sampler.addSample(sample);
		}
	}
}
