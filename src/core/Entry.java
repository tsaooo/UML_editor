package core;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Entry extends Application {
	private Scene main_scene;
	private Controller controller;
    @Override
    public void start(Stage primaryStage) {
    	creat_scene();
        primaryStage.setScene(this.main_scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

	public static void main(String[] args) {
        launch(args);
    }
	
	private void creat_scene() {
		VBox root = new VBox();
		HBox hbox = new HBox();
		
        VBox btn_area = new VBox();
        Button select = new Button("select");
        Button asso_line = new Button("Association");
        Button gener_line = new Button("generation");
        Button compo_line = new Button("composition");
        Button class_mode = new Button("class");
        Button useclass_mode = new Button("useclass");
        btn_area.getChildren().addAll(select, asso_line, gener_line,
        							compo_line, class_mode, useclass_mode);
        
        Canvas canvas = new Canvas(1024,768);
        hbox.getChildren().addAll(btn_area, canvas);
        MenuItem group = new MenuItem("Group");
        MenuItem ungroup = new MenuItem("Ungroup");
        MenuItem change = new MenuItem("Change object name");
        Menu edit_menu = new Menu("Edit"); 
        edit_menu.getItems().addAll(group, ungroup, change);
        
        MenuBar menubar = new MenuBar(new Menu("File"), edit_menu);
        root.getChildren().addAll(menubar, hbox);
        
        controller = new Controller(btn_area, canvas, menubar);
        this.main_scene = new Scene(root);
	}
}
