package fpstracker.core;

import fpstracker.ui.DesignUI;
import fpstracker.ui.UIPanel;
import processing.core.PApplet;

public class MemoryTracker extends BaseCustomTracker{
	private static final int MB = 1024 * 1024;
	private static int usedMemory = 0;
	
	public MemoryTracker(PApplet parent, int samplingSize) {
		super(parent);
		this.sampler = new SamplerMemory(this.parent, samplingSize, TrackerType.MEMORY);
		this.lastSample = (int) 0;
		this.ui = new UIPanel(this.parent, 110, 60, DesignUI.MEMORY.getUI());
	}
	
	public MemoryTracker(PApplet parent, int samplingSize, int width, int height) {
		super(parent);
		this.sampler = new SamplerMemory(this.parent, samplingSize, TrackerType.MEMORY);
		this.lastSample = (int) 0;
		this.ui = new UIPanel(this.parent, width, height, DesignUI.MEMORY.getUI());
	}

	@Override
	public void addSample() {
		if(this.sampler.isPlaying()) {
			Runtime runtime = Runtime.getRuntime();
		    usedMemory = (int) ((runtime.totalMemory() - runtime.freeMemory()) / MemoryTracker.MB);
		    ((SamplerMemory)this.sampler).setFreeMemory((int) (runtime.freeMemory() / MemoryTracker.MB));
		    ((SamplerMemory)this.sampler).setTotalMemory((int) (runtime.totalMemory() / MemoryTracker.MB));
		    ((SamplerMemory)this.sampler).setMaxMemorySize((int) (runtime.maxMemory() /  MemoryTracker.MB));
		    
			this.sampler.addSample(usedMemory);
			this.lastSample = usedMemory;
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
	
	public String toStringMinify() {
		return ((SamplerMemory)this.sampler).toStringMinify();
	}
}
