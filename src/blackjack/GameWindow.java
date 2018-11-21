package blackjack;

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
	private VBox rightVBox, leftVBox;
	private HBox topHBox, bottomHBox, centerHBox;
	private Scene scene;
	private Button hitButton, standButton, exitButton;
	private Label actionLabel, playerHandValueLabel, dealerHandValueLabel, statusLabel; // Labels what actions the user can do. Located at the right side of the window
	
	private BlackjackGame blackjackGame;
	
	private int width, height;

	public GameWindow() throws Exception {
		width = 1200;
		height = 900;
		blackjackGame = new BlackjackGame();
		blackjackGame.generateGame();
	}
	
	public GameWindow(int w, int h) throws Exception {
		width = w;
		height = h;
		blackjackGame = new BlackjackGame();
		blackjackGame.generateGame();
	}
	
	public void display() {
		window = new Stage();
		window.setTitle("Blackjack");
		
		// Layout of the window
		windowLayout = new BorderPane();
		
		// Scene Configuration
		scene = new Scene(windowLayout, width, height);
		scene.getStylesheets().add("GameWindowTheme.css");
		
		// RightVBox Configuration
		rightVBox = new VBox(10);
		rightVBox.setAlignment(Pos.BASELINE_CENTER);
		rightVBox.getStyleClass().add("right");
		windowLayout.setRight(rightVBox);
		
		//LeftVBox Configuration
		leftVBox = new VBox(10);
		leftVBox.setAlignment(Pos.BASELINE_CENTER);
		leftVBox.getStyleClass().add("left");
		playerHandValueLabel = new Label("Player Hand: " + blackjackGame.getPlayerHandValue() );
		dealerHandValueLabel = new Label("Dealer Hand: " + blackjackGame.getDealerHandValue() );
		windowLayout.setLeft(leftVBox);
		
		// TopHBox Configuration
		topHBox = new HBox(10);
		topHBox.setAlignment(Pos.CENTER);
		topHBox.getStyleClass().add("top");
		windowLayout.setTop(topHBox);
		
		// BottomHBox Configuration
		bottomHBox = new HBox();
		bottomHBox.setSpacing(10);
		bottomHBox.setAlignment(Pos.BOTTOM_CENTER);
		bottomHBox.getStyleClass().add("bottom");
		windowLayout.setBottom(bottomHBox);
		
		// CenterHBox Configuration
		centerHBox = new HBox(10);
		centerHBox.getStyleClass().add("center");
		centerHBox.setAlignment(Pos.CENTER);
		statusLabel = new Label("Blackjack");
		statusLabel.setAlignment(Pos.BASELINE_CENTER);
		windowLayout.setCenter(centerHBox);
		
		// Action Label Configuration
		actionLabel = new Label("Actions");
		
		// Hit Button Configuration
		hitButton = new Button("Hit");
		setHitButtonAction();
		
		// Stand Button Configuration
		standButton = new Button("Stand");
		setStandButtonAction();
		
		// Exit Button Configuration
		exitButton = new Button("Exit");
		exitButton.setOnAction(e -> {
			window.close();
		});
		
		// Adding child nodes to the layouts.
		rightVBox.getChildren().addAll(actionLabel, hitButton, standButton, exitButton);
		leftVBox.getChildren().addAll(playerHandValueLabel, dealerHandValueLabel);
		centerHBox.getChildren().addAll(statusLabel);

		setupHandsDisplay(); 
		
		window.setScene(scene);
		window.show();
		
	}
	
	// Helper Method that creates the shape and detail of a drawn card
	private void createCardDrawing(HBox hBox, Card card) {
		StackPane sp = new StackPane();
		hBox.getChildren().add(sp);
		
		Rectangle cardShape = new Rectangle(112, 175);
		cardShape.setFill(Color.TRANSPARENT);
		cardShape.setStroke(Color.BLACK);
		
		Label cardRankLabel = new Label(card.getRank() );
		
		Label ofLabel = new Label();
		
		Label cardSuitLabel = new Label();
		
		if (!blackjackGame.isHoleCard(card) ) {
			cardRankLabel = new Label(card.getRank() );
			StackPane.setAlignment(cardRankLabel, Pos.TOP_CENTER);
		
			ofLabel = new Label("of");
			StackPane.setAlignment(ofLabel, Pos.CENTER);
		
			cardSuitLabel = new Label(card.getSuit() );
			StackPane.setAlignment(cardSuitLabel, Pos.BOTTOM_CENTER);
			
			sp.getChildren().addAll(cardShape, cardRankLabel, ofLabel, cardSuitLabel);
		}
		
		else {
			Rectangle faceDown = new Rectangle(112, 175);
			faceDown.setFill(Color.TRANSPARENT);
			faceDown.setStroke(Color.BLACK);
			sp.getChildren().add(faceDown);
		}
	}
	
	// Helper method to configure the action for the Hit button.
	private void setHitButtonAction() {
		hitButton.setOnAction(e -> {
			
			Card card = new Card();
			try {
				card = blackjackGame.getDeck().draw();
				blackjackGame.addToPlayerHand(card);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			createCardDrawing(bottomHBox, card);
			
			playerHandValueLabel.setText("Player Hand: " + blackjackGame.getPlayerHandValue() );
			
		});
	}
	
	// Helper method to configure the action for the Stand button.
	private void setStandButtonAction() {
		standButton.setOnAction(e -> {
			blackjackGame.returnHoleCardValue();
			dealerHandValueLabel.setText("Dealer Hand: " + blackjackGame.getDealerHandValue() );
			
			
			if (blackjackGame.playerWon() ) {
				statusLabel.setText("Player Wins");
			}
			
			else {
				statusLabel.setText("Dealer Wins");
			}
		});
	}
	
	// Sets up the display of the starting hands for the player and dealer to the Game Window
	private void setupHandsDisplay() {
		for (int i = 0; i < blackjackGame.getStartingHandSize(); i++) {
			createCardDrawing(bottomHBox, blackjackGame.getPlayerHand().getCardAtIndex(i) );
			createCardDrawing(topHBox, blackjackGame.getDealerHand().getCardAtIndex(i) );
		}
	}
}
