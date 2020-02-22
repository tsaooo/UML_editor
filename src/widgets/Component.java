package widgets;

import javafx.scene.canvas.GraphicsContext;

public abstract class Component {
	protected boolean selected = false;
	protected String name;
	public abstract void draw(GraphicsContext gc);
	public abstract void move(double[] offset);
	public abstract boolean entered(double[] pos);
	public abstract boolean is_inside(double[]pos1, double[] pos2);
	
	public Component(){}
	public void select() {
		this.selected = true;
	}
	public void deselect() {
		this.selected = false;
	}
	public void setname(String name) {
		this.name = name;
	}
}
