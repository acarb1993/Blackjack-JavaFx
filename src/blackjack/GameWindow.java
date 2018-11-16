package blackjack;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameWindow {
	
	private Stage window;
	private BorderPane windowLayout;
	private VBox vbox;
	private HBox hbox;
	private Scene scene;
	private Button hitButton, stayButton, exitButton;
	private Label actionLabel, totalCardValue;
	
	private int width;
	private int height;

	public GameWindow() {
		width = 850;
		height = 900;
	}
	
	public GameWindow(int w, int h) {
		width = w;
		height = h;
	}
	
	public void display() {
		window = new Stage();
		window.setTitle("Blackjack");
		
		// Layout of the window
		windowLayout = new BorderPane();
		
		// Scene Configuration
		scene = new Scene(windowLayout, width, height);
		scene.getStylesheets().add("GameWindowVBoxTheme.css");
		
		// VBox Configuration
		vbox = new VBox(10);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.BASELINE_CENTER);
		vbox.getStyleClass().add("vbox");
		windowLayout.setRight(vbox);
		
		// HBox Configuration
		hbox = new HBox(10);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		windowLayout.setTop(hbox);
		
		// Action Label Configuration
		actionLabel = new Label("Actions");
		
		// Total Card Value Label Configuration
		totalCardValue = new Label("Total Card Value");
		
		// Hit Button Configuration
		hitButton = new Button("Hit");
		
		// Stay Button Configuration
		stayButton = new Button("Stay");
		
		// Exit Button Configuration
		exitButton = new Button("Exit");
		exitButton.setOnAction(e -> {
			window.close();
		});
		
		vbox.getChildren().addAll(actionLabel, hitButton, stayButton, exitButton);
		hbox.getChildren().addAll(totalCardValue);
		
		window.setScene(scene);
		window.show();
		
	}
}
