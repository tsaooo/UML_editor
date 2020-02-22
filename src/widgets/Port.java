package widgets;

import javafx.scene.canvas.GraphicsContext;

public class Port {
	private Widget parent;
	private DIR_enum side;
	final static double dot_leng = 7.5;
	
	Port(Widget parent, DIR_enum side){
		this.parent = parent;
		this.side = side;
	}
	double[] getPos() {
		return parent.get_side_pos(side);
	}
	void draw(GraphicsContext gc) {
		double[] pos = this.getPos();
		gc.fillRect(pos[0]-dot_leng/2, pos[1]-dot_leng/2, dot_leng, dot_leng);
	}
	
}
