package fpstracker.core;

import processing.core.PApplet;

public class SamplerMemory extends BaseSampler {
	protected int freeMemory;
	protected int totalMemory;
	protected int maxMemory;
	
	public SamplerMemory() {
	}
	
	public SamplerMemory(PApplet parent, int samplingSize, TrackerType type) {
		super(parent, samplingSize, type);
		this.freeMemory = 0;
		this.totalMemory = 0;
		this.maxMemory = 0;
	}
	
	protected void setMaxMemorySize(int maxMemory) {
		this.maxMemory = maxMemory;
	}
	
	protected void setTotalMemory(int totalMemory) {
		this.totalMemory = totalMemory;
	}
	
	protected void setFreeMemory(int freeMemory) {
		this.freeMemory = freeMemory;
	}

	public int getMaxMemorySize() {
		return this.maxMemory;
	}
	
	public int getTotalMemory() {
		return this.totalMemory;
	}
	
	public int getFreeMemory() {
		return this.freeMemory;
	}
}
