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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameWindow {
	
	private Stage window;
	private BorderPane windowLayout;
	private VBox rightVBox, leftVBox;
	private HBox topHBox, bottomHBox, centerHBox;
	private Scene scene;
	private Button hitButton, standButton, resetButton, exitButton;
	private Label actionLabel, playerHandValueLabel, dealerHandValueLabel, statusLabel; // Labels what actions the user can do. Located at the right side of the window
	private BlackjackGame blackjackGame;
	
	private int width, height;

	public GameWindow() {
		width = 1200;
		height = 900;
		
		blackjackGame = new BlackjackGame();
		blackjackGame.generateGame();
	}
	
	public void display() {
		// Window Configuration
		window = new Stage();
		window.setTitle("Blackjack");
		window.setMinWidth(width);
		window.setMinHeight(height);
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
		
		// Reset Button Configuration
		resetButton = new Button("Reset");
		setResetButtonAction();
		
		// Exit Button Configuration
		exitButton = new Button("Exit");
		setExitButtonAction();
		
		// Adding child nodes to the layouts.
		rightVBox.getChildren().addAll(actionLabel, hitButton, standButton, resetButton, exitButton);
		leftVBox.getChildren().addAll(playerHandValueLabel, dealerHandValueLabel);
		centerHBox.getChildren().addAll(statusLabel);

		// Creates the starting hand of the game
		setupHandsDisplay(); 
		
		window.setScene(scene);
		window.show();	
	}
	
	// Helper Method that creates the shape and detail of a drawn card, this will initally not show the hole card
	private void createCardDrawing(HBox hBox, Card card) {
		StackPane sp = new StackPane();
		hBox.getChildren().add(sp);
		
		Rectangle cardShape = new Rectangle(112, 175);
		cardShape.setFill(Color.TRANSPARENT);
		cardShape.setStroke(Color.BLACK);
		
		Text cardText = createCardText(card);
		sp.getChildren().addAll(cardShape, cardText);
	}
	
	// Helper method to draw a card that is not revealed to the screen
	private void createFaceDownCardDrawing(HBox hBox, Card card) {
		StackPane sp = new StackPane();
		hBox.getChildren().add(sp);
		
		Rectangle faceDown = new Rectangle(112, 175);
		faceDown.setFill(Color.TRANSPARENT);
		faceDown.setStroke(Color.BLACK);
		sp.getChildren().add(faceDown);
	}
	
	// Helper method to unveil the face down card in the dealers hand
	private StackPane replaceCardDrawing(Card card) {
		StackPane sp = new StackPane();
		
		Rectangle cardShape = new Rectangle(112, 175);
		cardShape.setFill(Color.TRANSPARENT);
		cardShape.setStroke(Color.BLACK);
		
		Text cardText = createCardText(card);
			
		sp.getChildren().addAll(cardShape, cardText);

		return sp;
	}
	
	// Helper method to configure the action for the Hit Button.
	private void setHitButtonAction() {
		hitButton.setOnAction(e -> {
			Card card = blackjackGame.getDeck().draw();
			blackjackGame.addToPlayerHand(card);
			createCardDrawing(bottomHBox, card);
			
			playerHandValueLabel.setText("Player Hand: " + blackjackGame.getPlayerHandValue() );
			
			if (blackjackGame.getPlayerHandValue() >= 21)
				standButton.fire();
		});
	}
	
	// Helper method to configure the action for the Stand Button.
	private void setStandButtonAction() {
		standButton.setOnAction(e -> {
			topHBox.getChildren().set(1, replaceCardDrawing(blackjackGame.getHoleCard() ) );
			
			blackjackGame.returnHoleCardValue();
			dealerHandValueLabel.setText("Dealer Hand: " + blackjackGame.getDealerHandValue() );
			
			if (blackjackGame.getDealerHandValue() < 16) {
				Card card = blackjackGame.getDeck().draw();
				blackjackGame.addToDealerHand(card);
				
				createCardDrawing(topHBox, card);
				
				dealerHandValueLabel.setText("Dealer Hand: " + blackjackGame.getDealerHandValue() );
			}
			
			if ((blackjackGame.getPlayerHandValue() > 21 && blackjackGame.getDealerHandValue() > 21) || 
				 blackjackGame.getPlayerHandValue() == blackjackGame.getDealerHandValue() ) {
				statusLabel.setText("Draw!");
			}
				
			else if (blackjackGame.playerWon() ) {
				statusLabel.setText("Player Wins");
			}
			
			else {
				statusLabel.setText("Dealer Wins");
			}
			
			hitButton.setDisable(true);
			standButton.setDisable(true);
		});
	}
	
	// Helper method to configure the action for the Reset Button
	private void setResetButtonAction() {
		resetButton.setOnAction(e -> {
			GameWindow gw = new GameWindow();
			gw.display();
			window.close();
		});
	}
	
	// Helper method to configure the action for the Exit Button
	private void setExitButtonAction() {
		exitButton.setOnAction(e -> {
			window.close();
		});
	}
	
	// Sets up the display of the starting hands for the player and dealer to the Game Window
	private void setupHandsDisplay() {
		for (int i = 0; i < blackjackGame.getStartingHandSize(); i++) {
			createCardDrawing(bottomHBox, blackjackGame.getPlayerHand().getCardAtIndex(i) );
			
			if (blackjackGame.getDealerHand().getCardAtIndex(i) != blackjackGame.getHoleCard() ) 
				createCardDrawing(topHBox, blackjackGame.getDealerHand().getCardAtIndex(i) );
			
			else 
				createFaceDownCardDrawing(topHBox, blackjackGame.getDealerHand().getCardAtIndex(i) );
		}
	}
	
	// Helper method to show the names of the cards in the Game Window
	private Text createCardText(Card c) {
		Text cardText = new Text(c.getRank() + "\n" + "of" + "\n" + c.getSuit() );
		cardText.setTextAlignment(TextAlignment.CENTER);
		cardText.setFont(new Font(23));
		return cardText;
	}
}
