package widgets;

import javafx.scene.canvas.GraphicsContext;

public class Generation_line extends Line {
	public Generation_line(Port start, Port end) {
		super(start, end);
	}
	@Override
	public void draw(GraphicsContext gc) {
		double[] start_point = startPort.getPos();
		double[] end_point = endPort.getPos();
		double[] dir_vector = {start_point[0]-end_point[0], start_point[1]-end_point[1]};
		double normalization = 9*1.41/Math.sqrt((Math.pow(dir_vector[0],2)+Math.pow(dir_vector[1], 2)));
		for(int i=0;i<dir_vector.length;i++)
			dir_vector[i] = dir_vector[i]*normalization;
		double clockwise = Math.toRadians(45);
		double counter_clockwise = Math.toRadians(315);
		double[] x_delta = {dir_vector[0]*Math.cos(counter_clockwise) - dir_vector[1]*Math.sin(counter_clockwise),
							dir_vector[0]*Math.cos(clockwise) - dir_vector[1]*Math.sin(clockwise)};
		double[] y_delta = {dir_vector[0]*Math.sin(counter_clockwise) + dir_vector[1]*Math.cos(counter_clockwise),
							dir_vector[0]*Math.sin(clockwise) + dir_vector[1]*Math.cos(clockwise)};
		double[] x_points = {end_point[0], end_point[0]+x_delta[0], end_point[0]+x_delta[1]};
		double[] y_points = {end_point[1], end_point[1]+y_delta[0], end_point[1]+y_delta[1]};
		gc.strokeLine(start_point[0], start_point[1], end_point[0]+dir_vector[0], end_point[1]+dir_vector[1]);
		gc.strokePolygon(x_points, y_points, 3);
	}
}
