package fpstracker.ui;
import java.util.List;

import fpstracker.core.BaseSampler;
import fpstracker.utils.MathsUtils;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;

public abstract class BaseUIPanel implements MathsUtils, PConstants{
	private PApplet parent;
	private PGraphics canvas;
	private int width, height;
	private int x, y;
	private float fontHeight;
	private int numberOfLines;
	private UI ui;

	public BaseUIPanel(PApplet parent, int width, int height, UI ui) {
		this.parent = parent;
		this.width = width;
		this.height = height;
		this.ui = ui;
		this.canvas = parent.createGraphics(this.width, this.height);
		this.x = 0;
		this.y = 0;
		this.fontHeight = this.ui.getFontSize();
	}
	
	public void display(int x, int y) {
		this.display(this.parent.g, x, y);
	}
	
	public void display(PGraphics context, int x, int y) {
		this.setXY(x, y);
		context.pushStyle();
		context.imageMode(CORNER);
		context.image(this.canvas, x, y);
		context.popStyle();
	}

	public void computePannel(String title, List list, Number min, Number max) {
		try {
			if(list != null && list.size() > 0) {
				this.canvas.beginDraw();
				this.canvas.background(this.ui.getBackgroundColor());
				this.computeHeader(title, 0, 0);
				this.fontHeight = this.getFontHeight();
				this.computeNumberOfLines(title);
				this.computeGraph(list, min, max, 0f, 0f + this.ui.getMargin() * 2 + this.fontHeight * this.numberOfLines);
				this.canvas.endDraw();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void computeHeader(String title, int x, int y) {
		this.canvas.pushStyle();
		this.canvas.fill(this.ui.getColor());
		this.canvas.noStroke();
		this.canvas.textAlign(LEFT, TOP);
		this.canvas.textSize(this.ui.getFontSize());
		this.canvas.text(title, x + this.ui.getMargin(), y + this.ui.getMargin(), this.width - this.ui.getMargin() * 2, this.height - this.ui.getMargin() * 2);
		this.canvas.popStyle();
	}

	private void computeNumberOfLines(String title) {
		this.numberOfLines = (int) Math.ceil(this.canvas.textWidth(title) / (this.canvas.width - this.ui.getMargin() * 2));
	}

	private void computeGraph(List list, Number min, Number max, float x, float y) {
		float nx = x; 
		float ny = y;
		float gwidth = this.width - nx * 2;
		float gheight = (int) (this.height - ny) + 1;
		float graphMargin = this.height * 0.25f;
		
		this.canvas.pushStyle();
		this.canvas.noStroke();
		this.canvas.fill(this.ui.getGraphBrackgroundColor());
		this.canvas.rect(nx, ny, gwidth, gheight);
		//graph
		this.canvas.beginShape();
		this.canvas.fill(this.ui.getGraphColor());
		this.canvas.vertex(nx + gwidth, ny + gheight);
		for(int i=0; i<list.size(); i++) {
			Number o = (Number) list.get(i);
			float normIndex = (float) i / (float) (list.size() - 1);
			float normValue = MathsUtils.normalize(o, min, max);
			float vy = (ny + gheight) - ((gheight - graphMargin) * normValue);
			float vx = nx + gwidth * (1.0f - normIndex);
			this.canvas.vertex(vx, vy);
		}
		this.canvas.vertex(nx, ny + gheight);
		this.canvas.endShape(CLOSE);
		this.canvas.popStyle();

	}


	public void setWidth(int width) {
		this.width = width;
		resize(this.width, this.height);
	}

	public void setHeight(int height) {
		this.height = height;
		resize(this.width, this.height);
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		resize(this.width, this.height);
	}

	private void resize(int width, int height) {
		canvas.resize(this.width, this.height);
	}

	public void setDesignUI(UI ui) {
		this.ui = ui;
	}
	
	private void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int[] getXY() {
		int[] xy = {this.x, this.y};
		return xy;
	}

	private float getFontHeight() {
		return this.canvas.textLeading;
	}
	
	public PGraphics getCanvas() {
		return this.canvas;
	}

}
