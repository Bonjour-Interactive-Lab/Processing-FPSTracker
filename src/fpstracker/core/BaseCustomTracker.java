package fpstracker.core;

import java.util.List;
import processing.core.*;

public abstract class BaseCustomTracker implements Sampling{
	protected PApplet parent;
	protected BaseSampler sampler;
	protected Object lastSample;

	public BaseCustomTracker() {}

	public BaseCustomTracker(PApplet parent) {
		this.parent = parent;
	}

	@Override
	public void play() {
		this.sampler.play();
	}

	@Override
	public void pause() {
		this.sampler.pause();
	}
	
	@Override
	public void playpause() {
		if(this.sampler.isPlaying()) {
			this.pause();
		}else {
			this.play();
		}
	}
	
	@Override
	public void computePanel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayPanel(PGraphics canvas, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSamplingSize(int size) {
		// TODO Auto-generated method stub
		if(size < this.sampler.getSamplingSize()) {
			int fromIndex = this.sampler.getSamplingSize() - size; 
			this.sampler.resetSampleListBetween(fromIndex, this.sampler.getSamplingSize());
		}
		this.sampler.setSampleSize(size);
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setColor(int color) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSamplingSize() {
		// TODO Auto-generated method stub
		return this.sampler.getSamplingSize();
	}

	@Override
	public List<Object> getSampleList() {
		return this.sampler.getSampleList();
	}

	@Override
	public Object getMinSample() {
		return this.sampler.getMinSample(); 
	}

	@Override
	public Object getMaxSample() {
		return this.sampler.getMaxSample();
	}

	@Override
	public boolean isPlaying() {
		return this.sampler.isPlaying();
	}

	@Override
	public PGraphics getPanel() {
		// TODO Auto-generated method stub
		return null;
	}
}
