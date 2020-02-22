package widgets;

import javafx.scene.canvas.GraphicsContext;

public class Association_line extends Line {
	public Association_line(Port start, Port end) {
		super(start, end);
	}
	@Override
	public void draw(GraphicsContext gc) {
		double[] startPos = startPort.getPos();
		double[] endPos = endPort.getPos();
		gc.strokeLine(startPos[0], startPos[1], endPos[0], endPos[1]);
	}
	
}