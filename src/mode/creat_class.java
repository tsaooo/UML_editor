package mode;

import core.Controller;
import widgets.Class_widget;

public class creat_class implements Mode{
	@Override
	public void draggedHandle(double[] pos) {}
	@Override
	public void releaseHandle(double[] pos) {}
	@Override
	public void pressHandle(double[] pos) {
		new_class(pos);
	}
	private void new_class(double[] pos) {
		Class_widget obj = new Class_widget(pos);
		Controller.add_widget(obj);
	}
}
