package fpstracker.ui;

public class UI{
	private int background; 
	private int color;
	private int graphBackground;
	private int graphColor;
	private float fontSize;
	private float margin;
	private float leading;

	public UI(int backgroundRed, int backgroundGreen, int backgroundBlue, 
			int colorRed, int colorGreen, int colorBlue, 
			int graphBackgroundRed, int graphBackgroundGreen, int graphBackgroundRBlue, 
			int graphColorRed, int graphColorGreen, int graphColorBlue,
			float fontSize, float margin){

		this.background = getRGBINtColor(backgroundRed, backgroundGreen, backgroundBlue);
		this.color = getRGBINtColor(colorRed, colorGreen, colorBlue);
		this.graphBackground = getRGBINtColor(graphBackgroundRed, graphBackgroundGreen, graphBackgroundRBlue);
		this.graphColor = getRGBINtColor(graphColorRed, graphColorGreen, graphColorBlue);
		this.fontSize = fontSize;
		this.margin = margin;
		this.leading = fontSize * 0.96f;
	}

	public int getRGBINtColor(int red, int green, int blue){
		return 255 << 24 | red << 16 | green << 8 | blue;
	}
	
	public float getFontSize() {
		return this.fontSize;
	}
	
	public int getBackgroundColor() {
		return this.background;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public int getGraphBrackgroundColor() {
		return this.graphBackground;
	}
	
	public int getGraphColor() {
		return this.graphColor;
	}
	
	public float getMargin() {
		return this.margin;
	}
	
	public float getLeading() {
		return this.leading;
	}
}
