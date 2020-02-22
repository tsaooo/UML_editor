package widgets;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Composite_widget extends Component{
	double[] start;
	double[] end;
	public ArrayList<Component> child_list;
	public Composite_widget(double[] start, double[] end, ArrayList<Component> childs) {
		this.start = start;
		this.end = end;
		this.child_list = childs;
	}
	@Override
	public void draw(GraphicsContext gc) {
		double widgh = end[0]-start[0];
		double height = end[1]-start[1];
		gc.save();
		if(this.selected)
			gc.setStroke(Color.RED);
		gc.strokeRoundRect(start[0], start[1], widgh, height, widgh/10, height/10);
		gc.restore();
		for(Component obj : this.child_list) 
			obj.draw(gc);
	}
	@Override
	public void move(double[] offset) {
		for(Component obj: child_list) 
			obj.move(offset);
		for(int i=0;i<start.length;i++) {
			start[i]+=offset[i];
			end[i]+=offset[i];
		}
	}
	@Override
	public boolean entered(double[] pos) {
		if(this.start[0]< pos[0] && pos[0]< this.end[0]	//pos is in the widget
				&& this.start[1]< pos[1] && pos[1]< this.end[1])
			return true;
		else return false;
	}
	@Override
	public boolean is_inside(double[] pos1, double[] pos2) {
		if(start[0]>=pos1[0]&&end[0]<=pos2[0]&&start[1]>=pos1[1]&&end[1]<=pos2[1])
			return true;
		else return false;
	}
}
