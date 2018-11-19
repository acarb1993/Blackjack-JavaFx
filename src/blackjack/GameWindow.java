package blackjack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameWindow {
	
	private Stage window;
	private BorderPane windowLayout;
	private VBox rightVbox;
	private HBox topHBox, bottomHBox;
	private Scene scene;
	private Button hitButton, standButton, exitButton;
	private Label actionLabel; // Labels what actions the user can do. Located at the right side of the window
	
	private BlackjackGame blackJackGame;
	
	private int width, height;

	public GameWindow() {
		width = 1200;
		height = 900;
		blackJackGame = new BlackjackGame();
		blackJackGame.generateCardValues();
	}
	
	public GameWindow(int w, int h) {
		width = w;
		height = h;
		blackJackGame = new BlackjackGame();
		blackJackGame.generateCardValues();
	}
	
	public void display() {
		window = new Stage();
		window.setTitle("Blackjack");
		
		// Layout of the window
		windowLayout = new BorderPane();
		
		// Scene Configuration
		scene = new Scene(windowLayout, width, height);
		scene.getStylesheets().add("GameWindowTheme.css");
		
		// LeftVBox Configuration
		rightVbox = new VBox(10);
		rightVbox.setSpacing(10);
		rightVbox.setAlignment(Pos.BASELINE_CENTER);
		rightVbox.getStyleClass().add("vbox");
		windowLayout.setRight(rightVbox);
		
		// TopHBox Configuration
		topHBox = new HBox(10);
		topHBox.setSpacing(10);
		topHBox.setAlignment(Pos.CENTER);
		topHBox.getStyleClass().add("hbox");
		windowLayout.setTop(topHBox);
		
		// BottomHBox Configuration
		bottomHBox = new HBox();
		bottomHBox.setSpacing(10);
		bottomHBox.setPadding(new Insets(height / 4, 0, 10, 0) );
		bottomHBox.setAlignment(Pos.BOTTOM_CENTER);
		bottomHBox.getStyleClass().add("hbox");
		windowLayout.setBottom(bottomHBox);
		
		// Action Label Configuration
		actionLabel = new Label("Actions");
		
		// Hit Button Configuration
		hitButton = new Button("Hit");
		hitButton.setOnAction(e -> {
			StackPane sp = new StackPane();
			bottomHBox.getChildren().add(sp);
			
			Rectangle r = new Rectangle(112, 175);
			r.setFill(Color.TRANSPARENT);
			r.setStroke(Color.BLACK);
			
			Card card = new Card();
			try {
				card = blackJackGame.getDeck().draw();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Label cardRankLabel = new Label(card.getRank() );
			StackPane.setAlignment(cardRankLabel, Pos.TOP_CENTER);
			
			Label ofLabel = new Label("of");
			StackPane.setAlignment(ofLabel, Pos.CENTER);
			
			Label cardSuitLabel = new Label(card.getSuit() );
			StackPane.setAlignment(cardSuitLabel, Pos.BOTTOM_CENTER);
			
			sp.getChildren().addAll(r, cardRankLabel, ofLabel, cardSuitLabel);
			
		});
		
		// Stand Button Configuration
		standButton = new Button("Stand");
		
		// Exit Button Configuration
		exitButton = new Button("Exit");
		exitButton.setOnAction(e -> {
			window.close();
		});
		
		rightVbox.getChildren().addAll(actionLabel, hitButton, standButton, exitButton);

		window.setScene(scene);
		window.show();
		
	}
}
