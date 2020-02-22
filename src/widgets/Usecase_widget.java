package widgets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Usecase_widget extends Widget {
	public Usecase_widget(double[] pos) {
		super(pos);
	}
	@Override
	final void draw_body(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.GRAY);
		gc.strokeOval(pos[0], pos[1], widgh, height);
		gc.fillOval(pos[0], pos[1], widgh, height);
	}
	@Override
	public boolean entered (double[] pos) {
		double w_square = Math.pow(widgh/2,2);
		double h_square = Math.pow(height/2,2);
		double[] center = new double[] {this.pos[0] + widgh/2, this.pos[1] + height/2};
		double x_square = Math.pow((pos[0]-center[0]), 2);
		double y_square = Math.pow((pos[1]-center[1]), 2);
		if(x_square/w_square + y_square/h_square <= 1) 
			return true;
		else return false;
	}
}
