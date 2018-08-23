package fpstracker.core;

import processing.core.*;
import java.util.List;

public interface Sampling {
	public void play();
	public void pause();
	public void playpause();

	public void computePanel();
	public void displayPanel(PGraphics canvas, int x, int y);
	
	public void setSamplingSize(int size);
	public void setWidth(int width);
	public void setHeight(int height);
	public void setSize(int width, int height);
	public void setColor(int color);
	
	public int getSamplingSize();
	public List<Object> getSampleList();
	public Object getMinSample();
	public Object getMaxSample();
	public boolean isPlaying();
	public PGraphics getPanel();
	
	public String toString();
}
