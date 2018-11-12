/*
 * A Java Project with elements of JavaFX. The player will be able to play the computer in a simple game of Blackjack.
 * Made by Adam Carballido
 */

package blackjack;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {	
	
	MainWindow mainWindow;
	
	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainWindow = new MainWindow();
		mainWindow.display(primaryStage);
	}
}
