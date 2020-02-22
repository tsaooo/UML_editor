package mode;

import core.Controller;
import widgets.DIR_enum;
import widgets.Line;
import widgets.Port;
import widgets.Widget;

public abstract class creat_line_widget implements Mode{
	private boolean dragging = false;
	double[] start_pos;
	double[] end_pos;
	@Override
	public void pressHandle(double[] pos) {
		start_pos = pos;
	}
	@Override
	public void draggedHandle(double[] pos) {
		dragging = true;
	}
	@Override
	public void releaseHandle(double[] pos) {
		if(dragging) {
			end_pos = pos;
			check_and_connection();
			dragging = false;
		}
	}
	private void check_and_connection() {
		DIR_enum connect_DIR_start = DIR_enum.none;
		DIR_enum connect_DIR_end = DIR_enum.none;
		Port connectedPort_1 = null;
		Port connectedPort_2 = null;
		for(Widget w: Controller.get_widget_list()) {
			if(connect_DIR_start == DIR_enum.none) {
				connect_DIR_start = w.get_enter_area(start_pos);
				connectedPort_1 = w.getPort(connect_DIR_start);
			}
			if(connect_DIR_end == DIR_enum.none) {
				connect_DIR_end = w.get_enter_area(end_pos);
				connectedPort_2 = w.getPort(connect_DIR_end);
			}
		}
		if(connect_DIR_start != DIR_enum.none && connect_DIR_end != DIR_enum.none) {
			Line line = get_line_widget(connectedPort_1, connectedPort_2);
			
			Controller.add_line(line);
			
		}
	}
	
	abstract Line get_line_widget(Port start, Port end);
}
