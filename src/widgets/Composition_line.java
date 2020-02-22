package widgets;

import javafx.scene.canvas.GraphicsContext;

public class Composition_line extends Line {
	public Composition_line(Port start, Port end) {
		super(start, end);
	}
	@Override
	public void draw(GraphicsContext gc) {
		double[] startPos = startPort.getPos();
		double[] endPos = endPort.getPos();
		double[] dir_vector = {startPos[0]-startPos[0], endPos[1]-endPos[1]};
		double normalization = 9*1.41/Math.sqrt((Math.pow(dir_vector[0],2)+Math.pow(dir_vector[1], 2)));
		for(int i=0;i<dir_vector.length;i++)
			dir_vector[i] = dir_vector[i]*normalization;
		double clockwise = Math.toRadians(45);
		double counter_clockwise = Math.toRadians(315);
		double[] x_delta = {dir_vector[0]*Math.cos(counter_clockwise) - dir_vector[1]*Math.sin(counter_clockwise),
							dir_vector[0]*1.41,
							dir_vector[0]*Math.cos(clockwise) - dir_vector[1]*Math.sin(clockwise)};
		double[] y_delta = {dir_vector[0]*Math.sin(counter_clockwise) + dir_vector[1]*Math.cos(counter_clockwise),
							dir_vector[1]*1.41,
							dir_vector[0]*Math.sin(clockwise) + dir_vector[1]*Math.cos(clockwise)};
		double[] x_points = {endPos[0], endPos[0]+x_delta[0], endPos[0]+x_delta[1], endPos[0]+x_delta[2]};
		double[] y_points = {endPos[1], endPos[1]+y_delta[0], endPos[1]+y_delta[1], endPos[1]+y_delta[2]};
		gc.strokeLine(startPos[0], startPos[1], endPos[0]+dir_vector[0]*1.41, endPos[1]+dir_vector[1]*1.41);
		gc.strokePolygon(x_points, y_points, 4);
	}
}
