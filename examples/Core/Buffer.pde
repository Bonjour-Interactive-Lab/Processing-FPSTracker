import java.util.*;

PGraphics buffer;
float margin = 10;

void initPanel(int w, int h){
  buffer = createGraphics(w, h);
}
//to set
//compute char size under a minimum size
//compute height text for graph position

void computePanel(String title, List<Object> list, Object min, Object max){
  buffer.beginDraw();
  buffer.background(30);
  computeHeader(buffer, title, 14, 0, 0, margin,color(255));
  computeGraph(buffer, list, min, max, 0, 25, margin, color(255));
  buffer.endDraw();
}


void computeHeader(PGraphics canvas, String title, float fontSize, float x, float y, float margin, int c){
  canvas.pushStyle();
  canvas.fill(c);
  canvas.noStroke();
  canvas.textAlign(LEFT, TOP);
  canvas.textSize(fontSize);
  canvas.text(title, x + margin, y + margin, canvas.width, canvas.height);
  canvas.popStyle();
}

void computeGraph(PGraphics canvas, List<Object> list, Object min, Object max, float x, float y, float margin, int c){
  float nx = x + margin;
  float ny = y + margin;
  float gwidth = canvas.width - nx * 2;
  float gheight = canvas.height - ny - margin;
  float graphMargin = gheight * 0.25;
  Object first = list.get(list.size() - 1);
  float normfirst = normalize(min, max, first);
  
  canvas.pushStyle();
  canvas.noStroke();
  canvas.fill(60);
  canvas.rect(nx, ny, gwidth, gheight);
  
  //graph
  canvas.beginShape();
  canvas.fill(255, 200 * normfirst + 55);
  canvas.vertex(nx + gwidth, ny + gheight);
  for(int i=0; i<list.size(); i++){
    Object o = list.get(i);
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

float normalize(Object min, Object max, Object value){
  float value_ = ((Number)value).floatValue();
  float min_ = ((Number)min).floatValue();
  float max_ = ((Number)max).floatValue();
   return  1.0 * ((value_ - min_) / (max_ -  min_));
}
