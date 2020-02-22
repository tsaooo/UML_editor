package mode;

import java.util.ArrayList;

import core.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import widgets.Component;
import widgets.Composite_widget;

public class select implements Mode{
	static private Component selected = null;
	ArrayList<Component> group_list = new ArrayList<Component>();
	static private boolean grouping = false;
	static private double[] start_pos;
	static private double[] end_pos;
	static private Menu edit_menu;
	MenuItem group;
    MenuItem ungroup;
    MenuItem change;
	
	public select(Menu menu){
		edit_menu = menu;
		set_menu_action();
	}
	public void pressHandle(double[] pos) {
		start_pos = pos;
		if(selected != null) 
			selected.deselect();
		if(!group_list.isEmpty()) {
			for(Component obj: group_list) 
				obj.deselect();
			group_list.clear();
		}
		selected = select_obj(pos);
	}
	public void draggedHandle(double[] pos) {
		if(selected != null) {
			double[] offset = new double[] {pos[0]-start_pos[0], pos[1]-start_pos[1]};
			if(offset[0]!=0 || offset[1]!=0) {
				selected.move(offset);
				start_pos = pos;
			}
		}
		else
			grouping = true;
	}
	public void releaseHandle(double[] pos) {
		if(grouping) {
			end_pos = pos;
			group_objs();
			grouping = false;
		}
		set_menu_disable();
	}
	private Component select_obj(double[] pos) {
		for(Component obj: Controller.get_component_list()) {
			if(obj.entered(pos)) {
				obj.select();
				return obj;
			}
		}
		return null;
	}
	private void group_objs() {
		standardize_group_pos();
		for(Component obj: Controller.get_component_list()) 
			if(obj.is_inside(start_pos, end_pos)) {
				obj.select();
				group_list.add(obj);
			}
	}
	private void standardize_group_pos() {
		//Standardize start_pos to be the left-top position, end_pos to be the right-down pos
		if(start_pos[0]>end_pos[0]) {
			start_pos[0] = start_pos[0] + end_pos[0];
			end_pos[0] = start_pos[0] - end_pos[0];
			start_pos[0] = start_pos[0] - end_pos[0];
		}
		if(start_pos[1]>end_pos[1]) {
			start_pos[1] = start_pos[1] + end_pos[1];
			end_pos[1] = start_pos[1] - end_pos[1];
			start_pos[1] = start_pos[1] - end_pos[1];
		}
	}
	private void set_menu_action() {
		group = edit_menu.getItems().get(0);
        ungroup = edit_menu.getItems().get(1);
        change = edit_menu.getItems().get(2);
        group.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ArrayList<Component> selected = new ArrayList<>(group_list);
				Composite_widget group_obj = new Composite_widget(start_pos, end_pos, selected);
				Controller.add_composite(group_obj);
				
			}
		});
        ungroup.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
			public void handle(ActionEvent event) {
				Controller.remove_composite((Composite_widget)selected);
				selected = null;
			}
		});
        change.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
			public void handle(ActionEvent event) {
        		Label text = new Label("Text : ");
        		final TextField textfield = new TextField();
        		GridPane layout = new GridPane();
        		layout.setAlignment(Pos.CENTER);
        		layout.add(text,0,0);
        		layout.add(textfield, 0, 1);
        		
        		Alert alert = new Alert(AlertType.CONFIRMATION);
        		alert.setTitle("Enter Name");
        		alert.setHeaderText(null);
        		alert.setContentText("Enter object name :");
        		alert.getDialogPane().setExpandableContent(layout);
        		alert.showAndWait().ifPresent(result -> {
        			if(result == ButtonType.OK) {
        				selected.setname(textfield.getText());
        			}
        		});
			}
		});
	}
	private void set_menu_disable() {
		group.setDisable(group_list.isEmpty());
		ungroup.setDisable(selected==null||!(selected instanceof Composite_widget));
	}
}
