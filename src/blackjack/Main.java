/*
 * A Java Project with elements of JavaFX. The player will be able to play the computer in a simple game of Blackjack.
 * Made by Adam Carballido
 */

package blackjack;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Main extends Application{
	
	private Stage window;
	private Scene scene;
	private GridPane layout;
	private Button playButton;
	private Button exitButton;
	private Label welcomeLabel;
	
	private int width = 450;
	private int height = 400;
	
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
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
