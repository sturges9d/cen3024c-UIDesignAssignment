/**
 * Created with guidance from TheNewBoston JavaFX tutorial, this class creates a confirmation box for a user to confirm their action.
 * 
 * @author Stephen Sturges Jr
 * @version 06/19/2022
 */

package cen3024c;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

	private static boolean answer;
	
	// Constants for consistent button and window sizing.
	private static final int buttonWidth = 50;
	private static final int buttonHeight = 20;
	private static final int windowWidth = 250;
	private static final int windowHeight = 75;
	private static final int insetsTop = 10;
	private static final int insetsRight = 10;
	private static final int insetsBottom = 10;
	private static final int insetsLeft = 10;
	
	public static boolean display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();
		label.setText(message);
		
		// Create buttons.
		Button yesButton = new Button("Yes");
		yesButton.setPrefSize(buttonWidth, buttonHeight);
		Button noButton  = new Button("No");
		noButton.setPrefSize(buttonWidth, buttonHeight);
		// Set button behavior.
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		// Set layout.
		HBox bottomMenu = new HBox();
		bottomMenu.setPadding(new Insets(insetsTop, insetsRight, insetsBottom, insetsLeft));
		bottomMenu.getChildren().addAll(noButton, yesButton);
		bottomMenu.setSpacing(windowWidth - ((2 * buttonWidth) + (insetsRight + insetsLeft)));
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(label);
		borderPane.setBottom(bottomMenu);
		
		// Add layout to scene.
		Scene scene = new Scene(borderPane, windowWidth, windowHeight);
		// Display window.
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}

}
