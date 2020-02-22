package widgets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

abstract public class Widget extends Component{
	protected double[] pos;
	final static double height = 75;
	final static double widgh = 100;
	
	private Port leftPort = null;
	private Port rightPort = null;
	private Port upPort = null;
	private Port downPort = null;
	private Port[] Ports = {leftPort, rightPort, upPort, downPort};
	
	abstract void draw_body(GraphicsContext gc);
	
	public Widget(double[] pos){
		this.pos = pos;
		int idx = 0;
		for(DIR_enum dir : DIR_enum.values()) {
			if(dir!=DIR_enum.none) {
				Ports[idx] = new Port(this, dir);
				idx+=1;
			}
		}
	}
	public DIR_enum get_enter_area(double[] pos) {
		if(entered(pos)) 
			return check_area(pos);
		else return DIR_enum.none;
	}
	@Override
	public void draw(GraphicsContext gc) {
		draw_body(gc);
		if(this.name!=null) {
			gc.setFill(Color.BLACK);
			gc.setFont(Font.font(15));
			gc.fillText(name, this.pos[0]+widgh/5, this.pos[1]+height/2);
		}
		if(this.selected) {
			//draw selected little point
			gc.setFill(Color.BLACK);
			for(Port port : Ports)
				port.draw(gc);
			gc.setFill(Color.GRAY);
		}
	}
	@Override
	public void move(double[] offset) {
		//move self pos data
		for(int i=0;i<pos.length;i++) {
			pos[i]+=offset[i];
		}
	}
	@Override
	public boolean is_inside(double[] pos1, double[] pos2) {
		if(this.pos[0]>=pos1[0]&&this.pos[0]+widgh<=pos2[0]
				&&this.pos[1]>=pos1[1]&&this.pos[1]+height<=pos2[1])
			return true;
		return false;
	}
	protected DIR_enum check_area(double[] pos) {
		//because possibility of overflow, can't simply calculate slope
		double deltaY = (pos[1]-(this.pos[1]+ height/2));
		double deltaX = (pos[0]-(this.pos[0]+ widgh/2));
		if(deltaX<0) {
			deltaY = -deltaY;
			deltaX = -deltaX;
		}
		double diagonal_slope = height/widgh;
		
		if(deltaX*diagonal_slope > deltaY && deltaX*diagonal_slope > -deltaY) {
			//pos is in the left/right side
			if (pos[0]<this.pos[0] + widgh/2) return DIR_enum.left;
			else return DIR_enum.right;
		}
		else {
			//pos is in the up/down side
			if (pos[1]<this.pos[1] + height/2) return DIR_enum.up;
			else return DIR_enum.down;
		}
	}
	public Port getPort(DIR_enum dir) {
		if(dir!=DIR_enum.none)
			return Ports[dir.ordinal()];
		return null;
	}
	public double[] get_side_pos(DIR_enum dir) {
		double[] side_pos = null;
		switch (dir) {
		case left:
			side_pos = new double[] {this.pos[0],this.pos[1]+height/2};
			break;
		case right:
			side_pos = new double[] {this.pos[0]+widgh,this.pos[1]+height/2};
			break;
		case up:
			side_pos = new double[] {this.pos[0]+widgh/2,this.pos[1]};
			break;
		case down:
			side_pos = new double[] {this.pos[0]+widgh/2,this.pos[1]+height};
			break;
		default:
			break;
		}
		return side_pos;
	}
	
}
