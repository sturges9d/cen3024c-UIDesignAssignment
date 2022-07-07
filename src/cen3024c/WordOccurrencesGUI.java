/**
 * This class is a modification of the CEN 3024C SDLC Assignment for the CEN3024C UI Design Assignment.
 * @author Stephen Sturges Jr
 * @version 06/26/2022
 */
package cen3024c;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WordOccurrencesGUI extends Application {
	
	private Stage window;
	private Scene scene1;
	private Scene scene2;
	
	// Constants for button and window sizes.
	private final int buttonWidth = 50;
	private final int buttonHeight = 20;
	private final int windowWidth = 500;
	private final int windowHeight = 500;
	private final int insetsTop = 10;
	private final int insetsRight = 10;
	private final int insetsBottom = 10;
	private final int insetsLeft = 10;
	
	URL textURL;
	String startText;
	String endText;
	
	/**
	 * Calls the ConfirmBox class to confirm user's choice to close the program.
	 */
	private void closeProgram() {
		Boolean answer = ConfirmBox.display("Confirmation", "Are you sure you want to exit?");
		if(answer) {
			window.close();
		}
	} // End of closeProgram method.
    
	/**
	 * This is the main method.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	} // End of main method.
	
	/**
	 * Overrides the start method from the Application class.
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Word Occurances");
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		
		// First scene (Splash screen).
		Label label1 = new Label("Welcome to my Word Occurrences program!");
		
		Label urlLabel = new Label("Please enter the page URL:");
		
		TextField urlField = new TextField("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
		urlField.setPromptText("Ex: https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
		
//		URL textURL = new URL(urlField.getText());
		
		Label startTextLabel = new Label("Please enter the first line of the text:");
		
		TextField startTextField = new TextField("The Raven");
		startTextField.setPromptText("Ex: The Raven");
		
		Label endTextLabel = new Label("Please enter the end line of the text:");
		
		TextField endTextField = new TextField("*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		endTextField.setPromptText("Ex: *** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		
		Button nextButton = new Button("Next");
		nextButton.setPrefSize(buttonWidth, buttonHeight);
		nextButton.setOnAction(e -> {
			try {
				textURL = new URL(urlField.getText());
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			startText = startTextField.getText();
			endText = endTextField.getText();
			window.setScene(scene2);
		});
		
		VBox centerMenu = new VBox();
		centerMenu.getChildren().addAll(label1, urlLabel, urlField, startTextLabel, startTextField, endTextLabel, endTextField);
		centerMenu.setPadding(new Insets(insetsTop, insetsRight, insetsBottom, insetsLeft));
		centerMenu.setAlignment(Pos.CENTER);
		
		VBox bottomMenu1 = new VBox();
		bottomMenu1.getChildren().addAll(nextButton);
		bottomMenu1.setPadding(new Insets(insetsTop, insetsRight, insetsBottom, insetsLeft));
		bottomMenu1.setAlignment(Pos.BOTTOM_RIGHT);
		
		BorderPane borderPane1 = new BorderPane();
		borderPane1.setCenter(centerMenu);
		borderPane1.setBottom(bottomMenu1);
		
		// Second scene (Results screen).
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(insetsTop, insetsRight, insetsBottom, insetsLeft));
		grid.setVgap(8);;
		grid.setHgap(10);
		
		Label listTitle = new Label("Top 20 most used words in \"" + startText + "\"");
		GridPane.setConstraints(listTitle, 0, 0);
		
		ArrayList<String> poemText = WordOccurrences.extractText(textURL, startText, endText);
		HashMap<String, Integer> results = WordOccurrences.convertArrayListToHashMap(poemText);
		HashMap<String, Integer> sortedResults = WordOccurrences.sortHashMap(results);
		String listResultString = WordOccurrences.convertHashMapToString(sortedResults);
		
		Label listResult = new Label(listResultString);
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
	} // End of start method.
    
} // End of WordOccurrencesGUI class.