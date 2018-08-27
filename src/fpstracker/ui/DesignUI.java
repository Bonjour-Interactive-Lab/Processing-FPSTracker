package fpstracker.ui;

public enum DesignUI {
	CUSTOM(new UI(33, 31, 19, 
			167, 147, 241, 
			44, 50, 64,
			167, 147, 241,
			11f, 3f)),

	FPS(new UI(33, 47, 48, 
			104, 233, 98, 
			44, 62, 64,
			104, 233, 98,
			11f, 3f)),

	MILLIS(new UI(33, 39, 48, 
			255, 222, 33, 
			44, 51, 64,
			255, 222, 33,
			11f, 3f)),

	MEMORY(new UI(38, 33, 48, 
			61, 158, 233, 
			50, 44, 64,
			61, 158, 233,
			11f, 3f));
	
	private UI ui = null;
	
	DesignUI(UI ui){
		this.ui = ui;
	}
	
	public UI getUI() {
		return this.ui;
	}
}
