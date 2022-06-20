/**
 * This class displays adds GUI functionality to the TextAnalyzer class via JavaFX.
 * 
 * @author Stephen Sturges Jr
 * @version 06/19/2022
 */
package cen3024c;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestClass extends Application {
	
	private Stage window;
	private Scene scene1, scene2;
	
	// Constants for consistent button and window sizing.
	private final int buttonWidth = 50;
	private final int buttonHeight = 20;
	private final int windowWidth = 300;
	private final int windowHeight = 430;
	private final int insetsTop = 10;
	private final int insetsRight = 10;
	private final int insetsBottom = 10;
	private final int insetsLeft = 10;
	
	/**
	 * This is the main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * This is the overridden start method from the Application class.
	 */
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Word Occurances");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		
		// First scene.
		Label label1 = new Label("Welcome to my Word Occurances program!");
		Button nextButton = new Button("Next");
		nextButton.setPrefSize(buttonWidth, buttonHeight);
		nextButton.setOnAction(e -> window.setScene(scene2));
		
		VBox bottomMenu1 = new VBox();
		bottomMenu1.getChildren().addAll(nextButton);
		bottomMenu1.setPadding(new Insets(insetsTop, insetsRight, insetsBottom, insetsLeft));
		bottomMenu1.setAlignment(Pos.BOTTOM_RIGHT);
		
		BorderPane borderPane1 = new BorderPane();
		borderPane1.setCenter(label1);
		borderPane1.setBottom(bottomMenu1);
		
		// Second scene.
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(insetsTop, insetsRight, insetsBottom, insetsLeft));
		grid.setVgap(8);;
		grid.setHgap(10);
		
		Label listTitle = new Label("Top 20 most used words in \"The Raven.\"");
		GridPane.setConstraints(listTitle, 0, 0);
		
		Label listResult = new Label(TextAnalyzer.analyzeText());
		GridPane.setConstraints(listResult, 0, 1);
		
		grid.getChildren().addAll(listTitle, listResult);
		
		Button backButton = new Button("Back");
		backButton.setPrefSize(buttonWidth, buttonHeight);
		backButton.setOnAction(e -> window.setScene(scene1));
		
		Button exitButton = new Button("Exit");
		exitButton.setPrefSize(buttonWidth, buttonHeight);
		exitButton.setOnAction(e -> closeProgram());
		
		HBox bottomMenu2 = new HBox();
		bottomMenu2.setPadding(new Insets(insetsTop, insetsRight, insetsBottom, insetsLeft));
		bottomMenu2.setSpacing(windowWidth - ((2 * buttonWidth) + (insetsRight + insetsLeft)));
		bottomMenu2.getChildren().addAll(backButton, exitButton);
		
		BorderPane borderPane2 = new BorderPane();
		borderPane2.setCenter(grid);
		borderPane2.setBottom(bottomMenu2);
		
		scene1 = new Scene(borderPane1, windowWidth, windowHeight);
		scene2 = new Scene(borderPane2, windowWidth, windowHeight);
		
		window.setScene(scene1);
		window.show();
	}

	/**
	 * This method calls the ConfirmBox class to confirm user's choice to close the program.
	 */
	private void closeProgram() {
		Boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to exit?");
		if(answer) {
			window.close();
		}
	}
}
