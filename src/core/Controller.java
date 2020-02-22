package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import widgets.*;
import mode.*;

public class Controller {
	static final ArrayList<Widget> widget_list = new ArrayList<>();
	static final ArrayList<Line> line_list = new ArrayList<>();
	static final ArrayList<Component> component_list = new ArrayList<Component>();
	
	private VBox btn_box;
	private Canvas canvas;
	private MenuBar menubar;
	private int selected_btn_idx;
	private List<Button> btn_list;
	private ArrayList<Mode> mode_list;
	private Mode current_mode;
	private Paint textfill;
	
	private AnimationTimer animation;	
	private GraphicsContext gc;
	
	public Controller(VBox btn_box, Canvas canvas, MenuBar menubar) {
		this.btn_box = btn_box;
		this.canvas = canvas;
		this.gc = this.canvas.getGraphicsContext2D();
		set_modes(menubar);
		set_btn_action();
		set_canvas_action();
		animation = new AnimationTimer() {
			@Override
			public void handle(long now) {
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				draw();
			}
		};
		animation.start();
	}
	private void set_btn_action() {
		Iterator<Mode> mode_iter = mode_list.iterator();
		btn_list =(List)btn_box.getChildren();
		for (Button btn: btn_list) {
			Mode mode = mode_iter.next();
			btn.setPrefSize(90, 90);

			btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					textfill = btn.getTextFill();
					current_mode = mode;					//warning
					deselect(selected_btn_idx);
					selected_btn_idx = btn_list.indexOf(btn);
					btn.setStyle("-fx-background-color: dimgrey");
					btn.setTextFill(Color.WHITESMOKE);
				}
			});
		}
	}
	private void set_canvas_action() {
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> 
									current_mode.pressHandle(new double[] {event.getX(), event.getY()}));
		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (event) -> 
									current_mode.draggedHandle(new double[] {event.getX(), event.getY()}));
		canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (event) -> 
									current_mode.releaseHandle(new double[] {event.getX(), event.getY()}));
	}
	private void set_modes(MenuBar menubar) {
		Menu edit_menu = menubar.getMenus().get(1);
		mode_list = new ArrayList<Mode>();
		mode_list.add(new select(edit_menu));
		mode_list.add(new creat_ass_line());
		mode_list.add(new creat_gener_line());
		mode_list.add(new creat_compo_line());
		mode_list.add(new creat_class());
		mode_list.add(new creat_usecase());
		current_mode = mode_list.get(0);
	}
	private void draw() {
		gc.setLineWidth(2);
		for(Component component: component_list) 
			component.draw(gc);
		for(Line line: line_list) 
			line.draw(gc);
	}
	
	private void deselect(int btn_idx) {
		Button btn = btn_list.get(btn_idx);
		btn.setStyle("");
		btn.setTextFill(textfill);
	}
	public static void add_widget(Widget obj) {
		widget_list.add(obj);
		component_list.add(obj);
	}
	public static void add_line(Line obj) {
		line_list.add(obj);
	}
	public static void add_composite(Composite_widget obj) {
		component_list.removeAll(obj.child_list);
		component_list.add(0, obj);
	}
	public static void remove_composite(Composite_widget obj) {
		component_list.addAll(obj.child_list);
		component_list.remove(obj);
	}
	public static ArrayList<Widget> get_widget_list() {
		return widget_list;
	}
	public static ArrayList<Line> get_line_list() {
		return line_list;
	}
	public static ArrayList<Component> get_component_list() {
		return component_list;
	}
}
	
	
