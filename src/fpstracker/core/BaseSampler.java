package fpstracker.core;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public abstract class BaseSampler{
	protected PApplet parent;
	protected int samplingSize;
	protected List<Number> sampleList;
	protected Number minSample, maxSample;
	protected boolean play = true;
	protected TrackerType type;

	public BaseSampler() {
	}


	public BaseSampler(PApplet parent, int samplingSize, TrackerType type) {
		this.parent = parent;
		this.samplingSize = samplingSize;
		this.type = type;
		this.sampleList = new ArrayList<Number>();
		//how to define float/int tracker (?)
	}

	protected void play() {
		this.play = true;
	}

	protected void pause() {
		this.play = false;
	}
	
	protected void addSample(Number sample) {
		defineMinMax(sample);
		addToListAndUpdate(sample);
	}
	
	private void defineMinMax(Number sample) {
		if(this.minSample == null) {
			this.minSample = sample;
		}else {
			//check if sample is min
			int test = compareNumbers(sample, this.minSample);
			this.minSample = (test <= 0) ? sample : this.minSample;
		}

		if(this.maxSample == null) {
			this.maxSample = sample;
		}else {
			//check if sample is max
			int test = compareNumbers(sample, this.maxSample);
			this.maxSample = (test > 0) ? sample : this.maxSample;
		}
	}

	
	private int compareNumbers(Number n1, Number n2)
    {
        Double n2c = n2.doubleValue();
        Double n1c = n1.doubleValue();

        return n1c.compareTo(n2c);
    }
	
	private void addToListAndUpdate(Number sample) {

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

	public List<Number> getSampleList(){
		return this.sampleList;
	}

	public Number getMinSample() {
		return this.minSample;
	}

	public Number getMaxSample() {
		return this.maxSample;
	}
	
	public Number getLastSample() {
		return this.sampleList.get(this.sampleList.size() - 1);
	}

	public boolean isPlaying() {
		return this.play;
	}

	public String toString() {
		return this.getLastSample() +" "+ type +" ["+ this.getMinSample()+"-"+this.getMaxSample()+"]";
	}
}