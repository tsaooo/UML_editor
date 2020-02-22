package mode;

import widgets.Composition_line;
import widgets.Line;
import widgets.Port;

public class creat_compo_line extends creat_line_widget{
	@Override
	Line get_line_widget(Port start, Port end) {
		Line line = new Composition_line(start, end);
		return line;
	}
}
