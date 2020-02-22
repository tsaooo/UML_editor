package widgets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Class_widget extends Widget {
	public Class_widget(double[] pos){
		super(pos);
	}
	@Override
	final void draw_body(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.GRAY);
		gc.strokeRect(pos[0], pos[1], widgh, height);
		gc.fillRect(pos[0], pos[1], widgh, height);
	}
	@Override
	public boolean entered(double[] pos) {
		if(this.pos[0]< pos[0] && pos[0]< this.pos[0] + widgh			//pos is in the widget
				&& this.pos[1]< pos[1] && pos[1]< this.pos[1] + height)
			return true;
		else return false;
	}
}
