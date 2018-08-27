package fpstracker.core;
import fpstracker.ui.*;
import java.util.List;
import processing.core.*;

public abstract class BaseCustomTracker implements Sampling{
	protected PApplet parent;
	protected BaseSampler sampler;
	protected Object lastSample;
	protected UIPanel ui;

	BaseCustomTracker() {}

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

	protected void addSample() {}

	@Override
	public void computePanel() {
		// TODO Auto-generated method stub
		if(this.isPlaying()) {
			this.ui.computePannel(this.toStringMinify(), this.getSampleList(), this.getMinSample(), this.getMaxSample());
		}
	}

	@Override
	public void displayPanel(PGraphics canvas, int x, int y) {
		// TODO Auto-generated method stub
		this.ui.display(canvas, x, y);
	}

	@Override
	public void displayPanel(int x, int y) {
		// TODO Auto-generated method stub
		this.ui.display(x, y);
	}

	@Override
	public void setSamplingSize(int size) {
		// TODO Auto-generated method stub
		if(size < this.sampler.getSampleList().size()) {
			int fromIndex = this.sampler.getSampleList().size() - size; 
			this.sampler.resetSampleListBetween(fromIndex, this.sampler.getSampleList().size());
		}
		this.sampler.setSampleSize(size);
	}

	@Override
	public void setWidth(int width) {
		// TODO Auto-generated method stub
		this.ui.setWidth(width);
	}

	@Override
	public void setHeight(int height) {
		// TODO Auto-generated method stub
		this.ui.setHeight(height);
	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		this.ui.setSize(width, height);
	}

	@Override
	public void setDesignUI(UI ui) {
		this.ui.setDesignUI(ui);
	};

	@Override
	public int getSamplingSize() {
		// TODO Auto-generated method stub
		return this.sampler.getSamplingSize();
	}

	@Override
	public List<Number> getSampleList() {
		return this.sampler.getSampleList();
	}

	@Override
	public Number getMinSample() {
		return this.sampler.getMinSample(); 
	}

	@Override
	public Number getMaxSample() {
		return this.sampler.getMaxSample();
	}

	@Override
	public boolean isPlaying() {
		return this.sampler.isPlaying();
	}

	@Override
	public PGraphics getPanel() {
		// TODO Auto-generated method stub
		return this.ui.getCanvas();
	}

	@Override
	public int[] getXY() {
		return this.ui.getXY();
	}
	
	@Override
	public TrackerType getType() {
		return this.sampler.getType();
	};

	@Override
	public String toString() {
		return this.sampler.toString();
	}

	@Override
	public String toStringMinify() {
		return this.sampler.toStringMinify();
	}
}
