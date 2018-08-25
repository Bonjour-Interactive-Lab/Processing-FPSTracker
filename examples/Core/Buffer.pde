import java.util.*;

PGraphics buffer;
float margin = 5;
float txtHeight;
int numberOfLine;

void initPanel(int w, int h) {
  buffer = createGraphics(w, h);
}
//to set
//compute char size under a minimum size
//compute height text for graph position

void computePanel(String title, List<Number> list, Number min, Number max, Design design) {
  buffer.beginDraw();
  buffer.background(design.background);
  computeHeader(buffer, title, 12, 0, 0, margin, design.col);

  defineTextHeight(buffer);  // -> this need to be defin once
  defineNumberOfLine(buffer, title, margin);
println(buffer.textLeading);
  computeGraph(buffer, list, min, max, 0, 0 + margin * 2 + txtHeight * numberOfLine, 0, design.graphBackground, design.graphColor);

  buffer.endDraw();
}


void computeHeader(PGraphics canvas, String title, float fontSize, float x, float y, float margin, int c) {
  canvas.pushStyle();
  canvas.fill(c);
  canvas.noStroke();
  canvas.textAlign(LEFT, TOP);
  canvas.textSize(fontSize);
  canvas.text(title, x + margin, y + margin, canvas.width, canvas.height);
  canvas.popStyle();
}

void defineTextHeight(PGraphics canvas) {
  txtHeight = canvas.textLeading;//canvas.textAscent() + canvas.textDescent();// +canvas.textLeading;
}

void defineNumberOfLine(PGraphics canvas, String title, float margin) {
  numberOfLine = (int) Math.ceil(canvas.textWidth(title) / (canvas.width - margin * 2));
}

void computeGraph(PGraphics canvas, List<Number> list, Number min, Number max, float x, float y, float margin, int c1, int c2) {
  float nx = x + margin;
  float ny = y + margin;
  float gwidth = canvas.width - nx * 2;
  float gheight = canvas.height - ny - margin;
  float graphMargin = gheight * 0.25;
  Number first = list.get(list.size() - 1);
  float normfirst = normalize(min, max, first);

  canvas.pushStyle();
  canvas.noStroke();
  canvas.fill(c1);
  canvas.rect(nx, ny, gwidth, gheight);

  //graph
  canvas.beginShape();
  canvas.fill(c2);
  canvas.vertex(nx + gwidth, ny + gheight);
  for (int i=0; i<list.size(); i++) {
    Number o = list.get(i);
    float normindex = (float) i / (float) (list.size() - 1);
    float normvalue = normalize(min, max, o);
    float vy = (ny + gheight) - ((gheight - graphMargin) * normvalue);
    float vx = nx + gwidth * (1.0 - normindex);
    canvas.vertex(vx, vy);
  }
  canvas.vertex(nx, ny + gheight);
  canvas.endShape(CLOSE);

  canvas.popStyle();
}

float normalize(Number min, Number max, Number value) {
  float valuef = ((Number)value).floatValue();
  float minf = ((Number)min).floatValue();
  float maxf = ((Number)max).floatValue();
  float normf = 1.0 * ((valuef - minf) / (maxf -  minf));
  return  (normf <= 1.0f) ? normf : 1.0f;
}
