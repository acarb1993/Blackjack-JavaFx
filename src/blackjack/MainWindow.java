package blackjack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {
	
	private Stage window;
	private Scene scene;
	private VBox windowLayout;
	private Button playButton;
	private Button exitButton;
	private Label welcomeLabel;
	private GameWindow gameWindow;
	
	
	private int width;
	private int height;

	// Constructor with a sensible default value
	public MainWindow() {
		width = 450;
		height = 400;
		gameWindow = new GameWindow();
		
	}
	
	public MainWindow(int w, int h) {
		width = w;
		height = h;
		gameWindow = new GameWindow();
	}
	
	// Displays the Main Window of the program, this will be the first thing the user sees, asking to play the game or exit.
	public void display(Stage w) { 
		
		window = w;
		window.setTitle("Main Menu");
		
		windowLayout = new VBox();
		windowLayout.setAlignment(Pos.CENTER);
		windowLayout.setPadding(new Insets(5, 5, 5, 5));
		windowLayout.setSpacing(10);
		
		scene = new Scene(windowLayout, width, height);
		scene.getStylesheets().add("MainWindowTheme.css");
		
		// Welcome Label Configuraton
		welcomeLabel = new Label("Welcome to Blackjack!");
		
		// Play Button Configuration
		playButton = new Button("Play");
		playButton.setOnAction(e -> {
			window.close();
			gameWindow.display();
		});
		
		// Exit Button Configuration
		exitButton = new Button("Exit");
		exitButton.setOnAction(e -> {
			window.close();
		});
		
		windowLayout.getChildren().addAll(welcomeLabel, playButton, exitButton);
		
		window.setScene(scene);
		window.show();
	}
}
