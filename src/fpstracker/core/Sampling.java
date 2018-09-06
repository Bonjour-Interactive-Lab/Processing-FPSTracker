package fpstracker.core;

import java.util.List;
import fpstracker.ui.UI;
import processing.core.PGraphics;

public interface Sampling {
	public void play();
	public void pause();
	public void playpause();

	public void computePanel();
	public void displayPanel(PGraphics canvas, int x, int y);
	public void displayPanel(int x, int y);
	
	public void setSamplingSize(int size);
	public void setWidth(int width);
	public void setHeight(int height);
	public void setSize(int width, int height);
	public void setDesignUI(UI ui);
	
	public int getSamplingSize();
	public void resetSamples();
	public List<Number> getSampleList();
	public Number getMinSample();
	public Number getMaxSample();
	public boolean isPlaying();
	public TrackerType getType();
	public PGraphics getPanel();
	public int[] getXY();
	
	public String toString();
	public String toStringMinify();
}
