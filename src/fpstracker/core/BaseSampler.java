package fpstracker.core;

import java.util.ArrayList;
import java.util.List;
import processing.core.*;

public abstract class BaseSampler {
	protected PApplet parent;
	protected int samplingSize;
	protected List<Object> sampleList;
	protected Object minSample, maxSample;
	protected boolean play = true;
	protected TrackerType type;

	public BaseSampler() {
	}
	

	public BaseSampler(PApplet parent, int samplingSize, TrackerType type) {
		this.parent = parent;
		this.samplingSize = samplingSize;
		this.type = type;
		this.sampleList = new ArrayList<Object>();
		//how to define float/int tracker (?)
	}

	protected void play() {
		this.play = true;
	}

	protected void pause() {
		this.play = false;
	}
	
	protected void addSample(double sample) {
		if(this.minSample == null) {
			this.minSample = sample;
		}else {
			//check if sample is min
			this.minSample = (sample < (int) this.minSample) ? sample : this.minSample;
		}

		if(this.maxSample == null) {
			this.maxSample = sample;
		}else {
			//check if sample is max
			this.maxSample = (sample > (int) this.maxSample) ? sample : this.maxSample;
		}

		//add sample to list
		this.sampleList.add(sample);

		//remove first sample if array is to large
		if(this.sampleList.size() > this.samplingSize)
			this.sampleList.remove(0);
	}

	protected void addSample(int sample) {
		if(this.minSample == null) {
			this.minSample = sample;
		}else {
			//check if sample is min
			this.minSample = (sample < (int) this.minSample) ? sample : this.minSample;
		}

		if(this.maxSample == null) {
			this.maxSample = sample;
		}else {
			//check if sample is max
			this.maxSample = (sample > (int) this.maxSample) ? sample : this.maxSample;
		}

		//add sample to list
		this.sampleList.add(sample);

		//remove first sample if array is to large
		if(this.sampleList.size() > this.samplingSize)
			this.sampleList.remove(0);
	}

	protected void addSample(float sample) {
		if(this.minSample == null) {
			this.minSample = sample;
		}else {
			//check if sample is min
			this.minSample = (sample < (float) this.minSample) ? sample : this.minSample;
		}

		if(this.maxSample == null) {
			this.maxSample = sample;
		}else {
			//check if sample is max
			this.maxSample = (sample > (float) this.maxSample) ? sample : this.maxSample;
		}

		//add sample to list
		this.sampleList.add(sample);

		//remove first sample if array is to large
		if(this.sampleList.size() > this.samplingSize)
			this.sampleList.remove(0);
	}

	public void setSampleSize(int samplingSize) {
		this.samplingSize = samplingSize;
	}
	
	protected void resetSampleListBetween(int fromIndex, int toIndex) {
		this.sampleList = this.sampleList.subList(fromIndex, toIndex);
	}

	public int getSamplingSize() {
		return this.samplingSize;
	}

	public List<Object> getSampleList(){
		return this.sampleList;
	}

	public Object getMinSample() {
		return this.minSample;
	}

	public Object getMaxSample() {
		return this.maxSample;
	}

	public boolean isPlaying() {
		return this.play;
	}

	public String toString() {
		return this.getClass().getName()+" "+this.getClass().hashCode();
	}
}
