/**
 * This class is a modification of the CEN 3024C SDLC Assignment for the CEN3024C UI Design Assignment.
 * Usage: readText > convertArrayListToHashMap > sortHashMap > convertHashMapToString
 * @author Stephen Sturges Jr
 * @version 06/19/2022
 */
package cen3024c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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

public class WordOccurrences {
    
    /**
     * Loops through the ArrayList, counts the number of occurrences of each word, and outputs a HashMap containing each word and its number of occurrences.
     * @param inputText An ArrayList of Strings. Each string is a line of text from the readText method. 
     * @return HashMap containing (word, number of occurrences) pairs.
     */
	public static HashMap<String, Integer> convertArrayListToHashMap(ArrayList<String> inputText) {
    	// Count the number of occurrences of a word in the ArrayList of text from the URL and store the word and its number of occurrences in a HashMap.
        HashMap<String, Integer> results = new HashMap<>();
        for (int i = 0; i < inputText.size(); i++) {
            int wordCount = 0;
            String word = inputText.get(i);
            for (int j = 0; j < inputText.size(); j++) {
                if (inputText.get(j).equalsIgnoreCase(word)) {
                    wordCount++;
                    inputText.remove(j);
                } // End of if statement.
            } // End of for loop.
            results.put(word, wordCount);
            wordCount = 0;
        } // End of for loop.
        return results;
    } // End of convertArrayListToHashMap() method.
    
    /**
     * Converts a sorted HashMap into Strings ending in a new line character for display.
     * @param inputText A HashMap containing a word and its number of occurrences.
     * @return String in the format: "#. word, number_of_occurrences"
     */
    public static String convertHashMapToString(HashMap<String, Integer> inputText) {
    	String result = ""; // Used for output to TestClass GUI.
        Set<Entry<String, Integer>> entrySetSortedByValue = inputText.entrySet();
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
    } // End of convertHashMapToString method.
    
    /**
     * Reads the text from a webpage.
     * @param url The URL of the webpage you wish to extract text from.
     * @param startText The first line of the text you want to extract.
     * @param endText The line after the last line you want to extract.
     * @return ArrayList of lower case strings.
     */
    public static ArrayList<String> readText(String inputURL, String startText, String endText) {
    	ArrayList<String> result = new ArrayList<String>();
    	URL url = null;
		try {
			url = new URL(inputURL);
		} catch (MalformedURLException e1) {
			System.out.println("Malformed URL Exception: Make sure the input value for the URL is valid.");
			e1.printStackTrace();
		} // End of try-catch block.
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
                if (inputLine.equalsIgnoreCase(startText)) {
                    printLines = true;
                } // End of if statement.
                
                // Removes empty lines from being included in the relevant text ArrayList.
                if (inputLine.isEmpty()) {
                    emptyLine = true;
                } else {
                    emptyLine = false;
                } // End of if-else statement.
                
                // Controls the end of the relevant text to analyze.
                if (inputLine.equalsIgnoreCase(endText)) {
                    printLines = false;
                    readInput = false;
                } // End of if statement.
                
                // Splits the String lines into string words and places them into the textToAnalyze array.
                if (printLines == true && emptyLine == false) {
                    String outputTextArray[] = inputLine.split("&mdash|[^'a-z[A-Z]]");
                    for (int i = 0; i < outputTextArray.length; i++) {
                        if (!outputTextArray[i].isEmpty()) {
                            result.add(outputTextArray[i].trim().toLowerCase());
                        } // End of if statement.
                    } // End of for loop.
                } // End of if statement.
            } // End of while loop.
    	} catch (Exception e) {
            e.printStackTrace();
        } // End of try-catch statement.
    	return result;
    } // End of readText method.
    
    /**
     * Sorts the values stored in a HashMap according to the integer value, greatest to least.
     * @param inputText HashMap with String keys and Integer values containing a word and its number of occurrences.
     * @return Sorted HashMap of words from highest occurrence to lowest.
     */
    public static HashMap<String, Integer> sortHashMap(HashMap<String, Integer> inputText) {
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
        Set<Entry<String, Integer>> resultsSet = inputText.entrySet();

        // Create a new ArrayList from the Set.
        List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(resultsSet);

        // Sort the ArrayList using the custom comparator.
        Collections.sort(listOfEntries, valueComparator);

        // Create a new LinkedHashMap to store the values from the sorted ArrayList.
        LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>(listOfEntries.size());

        // Place the key and value pairs into the new LinkedHashMap.
        for (Entry<String,Integer> entry : listOfEntries) {
        	result.put(entry.getKey(), entry.getValue());
        } // End of for loop.
        return result;
    } // End of sortHashMap method.

} // End of WordOccurrences class.