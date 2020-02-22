package mode;

import widgets.Association_line;
import widgets.Line;
import widgets.Port;

public class creat_ass_line extends creat_line_widget{
	Line get_line_widget(Port start, Port end) {
		Line line = new Association_line(start, end);
		return line;
	}
}
