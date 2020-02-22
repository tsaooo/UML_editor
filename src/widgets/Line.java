package widgets;

import javafx.scene.canvas.GraphicsContext;

public abstract class Line {
	Port startPort;
	Port endPort;
	public Line(Port start, Port end) { 
		this.startPort = start;
		this.endPort = end;
	}
	
	public abstract void draw(GraphicsContext gc);
	
}
