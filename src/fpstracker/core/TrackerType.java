package fpstracker.core;

public enum TrackerType {
	FPS("FPS"),
	MILLIS("MS"),
	MEMORY("MB"),
	CUSTOM("Custom");
	
	private String name = "";
	
	TrackerType(String name){
		this.name= name;
	}
	
	public String toString() {
		return this.name;
	}
}
