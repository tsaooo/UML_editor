package mode;

import widgets.Generation_line;
import widgets.Line;
import widgets.Port;

public class creat_gener_line extends creat_line_widget{
	@Override
	Line get_line_widget(Port start, Port end) {
		Line line = new Generation_line(start, end);
		return line;
	}
}