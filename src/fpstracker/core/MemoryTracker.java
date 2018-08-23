package fpstracker.core;

import processing.core.PApplet;

public class MemoryTracker extends BaseCustomTracker{
	private static final int MB = 1024 * 1024;
	
	public MemoryTracker(PApplet parent, int samplingSize) {
		super(parent);
		this.sampler = new SamplerMemory(this.parent, samplingSize, TrackerType.FPS);
	}

	public void addSample() {
		if(this.sampler.isPlaying()) {
			Runtime runtime = Runtime.getRuntime();
		    int usedMemory = (int) ((runtime.totalMemory() - runtime.freeMemory()) / this.MB);
		    ((SamplerMemory)this.sampler).setFreeMemory((int) (runtime.freeMemory() / this.MB));
		    ((SamplerMemory)this.sampler).setTotalMemory((int) (runtime.totalMemory() / this.MB));
		    ((SamplerMemory)this.sampler).setMaxMemorySize((int) (runtime.maxMemory() /  this.MB));
		    
			this.sampler.addSample(usedMemory);
			this.lastSample = (int) usedMemory;
		}
	}
	
	public int getMaxMemorySize() {
		return ((SamplerMemory)this.sampler).getMaxMemorySize();
	}
	
	public int getTotalMemory() {
		return ((SamplerMemory)this.sampler).getTotalMemory();
	}
	
	public int getFreeMemory() {
		return ((SamplerMemory)this.sampler).getFreeMemory();
	}
}
