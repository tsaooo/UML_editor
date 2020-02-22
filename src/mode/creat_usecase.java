package mode;

import core.Controller;
import widgets.Usecase_widget;

public class creat_usecase implements Mode{

	public void draggedHandle(double[] cusor) {}
	public void releaseHandle(double[] cusor) {}
	@Override
	public void pressHandle(double[] pos) {
		new_useclass(pos);
	}
	private void new_useclass(double[] pos) {
		Usecase_widget obj = new Usecase_widget(pos);
		Controller.add_widget(obj);
	}
}
