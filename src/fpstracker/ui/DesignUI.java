package fpstracker.ui;

public enum DesignUI {
	Custom(33, 31, 19, 
			167, 147, 241, 
			44, 50, 64,
			167, 147, 241),

	FPS(33, 47, 48, 
			104, 233, 98, 
			44, 62, 64,
			104, 233, 98),

	Millis(33, 39, 48, 
			255, 222, 33, 
			44, 51, 64,
			255, 222, 33),

	Memory(38, 33, 48, 
			61, 158, 233, 
			50, 44, 64,
			61, 158, 233);

	private int background; 
	private int color;
	private int graphBackground;
	private int graphColor;

	DesignUI(int backgroundRed, int backgroundGreen, int backgroundBlue, 
			int colorRed, int colorGreen, int colorBlue, 
			int graphBackgroundRed, int graphBackgroundGreen, int graphBackgroundRBlue, 
			int graphColorRed, int graphColorGreen, int graphColorBlue){

		this.background = getRGBColor(backgroundRed, backgroundGreen, backgroundBlue);
		this.color = getRGBColor(colorRed, colorGreen, colorBlue);
		this.graphBackground = getRGBColor(graphBackgroundRed, graphBackgroundGreen, graphBackgroundRBlue);
		this.graphColor = getRGBColor(graphColorRed, graphColorGreen, graphColorBlue);
	}

	private int getRGBColor(int red, int green, int blue){
		return 255 << 24 | red << 16 | green << 8 | blue;
	}
}
