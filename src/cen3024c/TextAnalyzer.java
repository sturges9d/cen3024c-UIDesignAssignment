/**
 * This class is a modification of the CEN 3024C SDLC Assignment for the CEN3024C UI Design Assignment.
 * 
 * @author Stephen Sturges Jr
 * @version 06/19/2022
 */
package cen3024c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

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

public class TextAnalyzer extends Application {
	private Stage window;
	private Scene scene1;
	private Scene scene2;
	
	// Constants for button and window sizes.
	private final int buttonWidth = 50;
	private final int buttonHeight = 20;
	private final int windowWidth = 300;
	private final int windowHeight = 430;
	private final int insetsTop = 10;
	private final int insetsRight = 10;
	private final int insetsBottom = 10;
	private final int insetsLeft = 10;
	
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
     * Converts an ArrayList to HashMap.
     * 
     * @param textToAnalyze
     * @return
     */
    protected static HashMap<String, Integer> convertArrayListToHashMap(ArrayList<String> textToAnalyze) {
    	// Count the number of occurrences of a word in the ArrayList of text from the URL and store the word and its number of occurrences in a HashMap.
        HashMap<String, Integer> results = new HashMap<>();
        for (int i = 0; i < textToAnalyze.size(); i++) {
            int wordCount = 0;
            String word = textToAnalyze.get(i);
            for (int j = 0; j < textToAnalyze.size(); j++) {
                if (textToAnalyze.get(j).equalsIgnoreCase(word)) {
                    wordCount++;
                    textToAnalyze.remove(j);
                } // End of if statement.
            } // End of for loop.
            results.put(word, wordCount);
            wordCount = 0;
        } // End of for loop.
        return results;
    } // End of convertArrayListToHashMap() method.
	
	/**
	 * Reads the text from a webpage.
	 * 
	 * @param url The URL of the website you wish to extract text from.
	 * @param startTextLine The first line of the text you want to extract.
	 * @param endTextLine The line after the last line you want to extract.
	 * @return ArrayList of lower case strings.
	 */
    public static ArrayList<String> extractText(URL url, String startTextLine, String endTextLine) {
    	ArrayList<String> textToAnalyze = new ArrayList<String>();
    	try {
            BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            // Define loop control variables and create ArrayList for analysis.
            Boolean readInput = true;
            Boolean printLines = false;
            Boolean emptyLine = false;
            String inputLine;
            
            /**
             * Replaces all HTML tags with nothing and replace the special character ’ with '.
             * If the string with all replacements is null, or the readInput variable is false, then exit the loop.
             */
            while ((inputLine = input.readLine().replaceAll("<[^>]*>", "").replaceAll("’", "'")) != null && readInput) {
                // Controls the start of the relevant text to analyze.
                if (inputLine.equalsIgnoreCase(startTextLine)) {
                    printLines = true;
                } // End of if statement.
                
                // Removes empty lines from being included in the relevant text ArrayList.
                if (inputLine.isEmpty()) {
                    emptyLine = true;
                } else {
                    emptyLine = false;
                } // End of if-else statement.
                
                // Controls the end of the relevant text to analyze.
                if (inputLine.equalsIgnoreCase(endTextLine)) {
                    printLines = false;
                    readInput = false;
                } // End of if statement.
                
                // Splits the String lines into string words and places them into the textToAnalyze array.
                if (printLines == true && emptyLine == false) {
                    String outputTextArray[] = inputLine.split("&mdash|[^'a-z[A-Z]]");
                    for (int i = 0; i < outputTextArray.length; i++) {
                        if (!outputTextArray[i].isEmpty()) {
                            textToAnalyze.add(outputTextArray[i].trim().toLowerCase());
                        } // End of if statement.
                    } // End of for loop.
                } // End of if statement.
            } // End of while loop.
    	} catch (Exception e) {
            e.printStackTrace();
        } // End of try-catch statement.
    	return textToAnalyze;
    } // End of extractText method.
    
	/**
	 * This is the main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Overrides the start method from the Application class.
	 * 
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
		URL textURL = new URL(urlField.getText());
		Label startTextLabel = new Label("Please enter the first line of the text:");
		TextField startTextField = new TextField("The Raven");
		Label endTextLabel = new Label("Please enter the end line of the text:");
		TextField endTextField = new TextField("*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		
		// Used for testing another poem.
//		TextField urlField = new TextField("https://www.gutenberg.org/files/68410/68410-h/68410-h.htm");
//		Label urlLabel = new Label("Please enter the page URL:");
//		URL textURL = new URL(urlField.getText());
//		Label startTextLabel = new Label("Please enter the first line of the text:");
//		TextField startTextField = new TextField("DENY THE SLAKE");
//		Label endTextLabel = new Label("Please enter the end line of the text:");
//		TextField endTextField = new TextField("*** END OF THE PROJECT GUTENBERG EBOOK DENY THE SLAKE ***");
		
		Button nextButton = new Button("Next");
		nextButton.setPrefSize(buttonWidth, buttonHeight);
		nextButton.setOnAction(e -> {
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
		
		Label listTitle = new Label("Top 20 most used words in \"" + startTextField.getText() + "\"");
		GridPane.setConstraints(listTitle, 0, 0);
		
		ArrayList<String> poemText = extractText(textURL, startTextField.getText(), endTextField.getText());
		HashMap<String, Integer> results = convertArrayListToHashMap(poemText);
		String sortedResults = sortHashMap(results);
		
		Label listResult = new Label(sortedResults);

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

    /**
     * Sorts the values stored in a HashMap
     * 
     * @param results
     * @return
     */
    protected static String sortHashMap(HashMap<String, Integer> results) {
        // Custom comparator to order the values (occurrences) from greatest to least.
        Comparator<Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                Integer v1 = e1.getValue();
                Integer v2 = e2.getValue();
                String k1 = e1.getKey();
                String k2 = e2.getKey();
                // If the number of occurrences are equal, sort the words in alphabetical order.
                if (v2 == v1) {
                    return k1.compareTo(k2);
                } else {
                    return v2.compareTo(v1);
                } // End of if-else statement.
            } // End of compare method.
        };
        
        // Place all the entries from the results HashMap into a Set.
        Set<Entry<String, Integer>> resultsSet = results.entrySet();

        // Create a new ArrayList from the Set.
        List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(resultsSet);

        // Sort the ArrayList using the custom comparator.
        Collections.sort(listOfEntries, valueComparator);

        // Create a new LinkedHashMap to store the values from the sorted ArrayList.
        LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String, Integer>(listOfEntries.size());

        // Place the key and value pairs into the new LinkedHashMap.
        for (Entry<String,Integer> entry : listOfEntries) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        } // End of for loop.

        // Output results to a String.
        String result = ""; // Used for output to TestClass GUI.
        Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();
        int i = 0;
        for(Entry<String, Integer> mapping : entrySetSortedByValue) {
            i++;
            result += (i + ". " + mapping.getKey() + ", " + mapping.getValue() + "\n");
            // Stop the list at 20 entries.
            if (i == 20) {
                break;
            } // End of if statement.
        } // End of for loop.
	return result;
    } // End of sortHashMap method.
    
} // End of TextAnalyzer Class.