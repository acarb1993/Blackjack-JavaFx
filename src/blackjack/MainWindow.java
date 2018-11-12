package blackjack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow {
	
	private Stage window;
	private Scene scene;
	private GridPane layout;
	private Button playButton;
	private Button exitButton;
	private Label welcomeLabel;
	
	private int width;
	private int height;

	// Constructor with a sensible default value
	public MainWindow() {
		width = 450;
		height = 400;
	}
	
	public MainWindow(int w, int h) {
		width = w;
		height = h;
	}
	
	// Displays the Main Window of the program, this will be the first thing the user sees, asking to play the game or exit.
	public void display(Stage w) {
		
		window = w;
		window.setTitle("Blackjack");
		
		layout = new GridPane();
		layout.setPadding(new Insets(5, 5, 5, 5));
		layout.setVgap(8);
		layout.setHgap(10);
		
		scene = new Scene(layout, width, height);
		scene.getStylesheets().add("MainWindowTheme.css");
		
		// Welcome Label Configuraton
		welcomeLabel = new Label("Welcome to Blackjack!");
		GridPane.setConstraints(welcomeLabel, 0, 0);
		
		// Play Button Configuration
		playButton = new Button("Play");
		GridPane.setConstraints(playButton, 0, 1);
		
		// Exit Button Configuration
		exitButton = new Button("Exit");
		exitButton.setOnAction(e -> {
			window.close();
		});
		GridPane.setConstraints(exitButton, 0, 2);
		
		layout.getChildren().addAll(welcomeLabel, playButton, exitButton);
		layout.setAlignment(Pos.CENTER);
		
		window.setScene(scene);
		window.show();
	}
}
