package fpstracker.ui;

public enum DesignUI {
	CUSTOM(new UI(33, 31, 19, 
			167, 147, 241, 
			44, 50, 64,
			167, 147, 241,
			13f, 5f)),

	FPS(new UI(33, 47, 48, 
			104, 233, 98, 
			44, 62, 64,
			104, 233, 98,
			13f, 5f)),

	MILLIS(new UI(33, 39, 48, 
			255, 222, 33, 
			44, 51, 64,
			255, 222, 33,
			13f, 5f)),

	MEMORY(new UI(38, 33, 48, 
			61, 158, 233, 
			50, 44, 64,
			61, 158, 233,
			13f, 5f));
	
	private UI ui = null;
	
	DesignUI(UI ui){
		this.ui = ui;
	}
	
	public UI getUI() {
		return this.ui;
	}
	
	/*CUSTOM(33, 31, 19, 
			167, 147, 241, 
			44, 50, 64,
			167, 147, 241,
			13f, 5f),

	FPS(33, 47, 48, 
			104, 233, 98, 
			44, 62, 64,
			104, 233, 98,
			13f, 5f),

	MILLIS(33, 39, 48, 
			255, 222, 33, 
			44, 51, 64,
			255, 222, 33,
			13f, 5f),

	MEMORY(38, 33, 48, 
			61, 158, 233, 
			50, 44, 64,
			61, 158, 233,
			13f, 5f);

	private int background; 
	private int color;
	private int graphBackground;
	private int graphColor;
	private float fontSize;
	private float margin;

	DesignUI(int backgroundRed, int backgroundGreen, int backgroundBlue, 
			int colorRed, int colorGreen, int colorBlue, 
			int graphBackgroundRed, int graphBackgroundGreen, int graphBackgroundRBlue, 
			int graphColorRed, int graphColorGreen, int graphColorBlue,
			float fontSize, float margin){

		this.background = getRGBColor(backgroundRed, backgroundGreen, backgroundBlue);
		this.color = getRGBColor(colorRed, colorGreen, colorBlue);
		this.graphBackground = getRGBColor(graphBackgroundRed, graphBackgroundGreen, graphBackgroundRBlue);
		this.graphColor = getRGBColor(graphColorRed, graphColorGreen, graphColorBlue);
		this.fontSize = fontSize;
		this.margin = margin;
	}

	private int getRGBColor(int red, int green, int blue){
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
	}*/
}
