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

public class TextAnalyzer{
    public static String analyzeText() {
    	String result = "";
    	try {
            URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
            BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            // Define loop control variables and create ArrayList for analysis.
            Boolean readInput = true;
            Boolean printLines = false;
            Boolean emptyLine = false;
            ArrayList<String> textToAnalyze = new ArrayList<String>();
            String inputLine;
            while ((inputLine = input.readLine().replaceAll("<[^>]*>", "").replaceAll("’", "'")) != null && readInput) {
                // Controls the start of the relevant text to analyze.
                if (inputLine.equalsIgnoreCase("The Raven")) {
                    printLines = true;
                }
                // Removes empty lines from being included in the relevant text ArrayList.
                if (inputLine.isEmpty()) {
                    emptyLine = true;
                } else {
                    emptyLine = false;
                }
                // Controls the end of the relevant text to analyze.
                if (inputLine.equalsIgnoreCase("*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***")) {
                    printLines = false;
                    readInput = false;
                }
                // Splits the String lines into string words and places them into the textToAnalyze array.
                if (printLines == true && emptyLine == false) {
                    String outputTextArray[] = inputLine.split("&mdash|[^’'a-z[A-Z]]");
                    for (int i = 0; i < outputTextArray.length; i++) {
                        if (!outputTextArray[i].isEmpty()) {
                            textToAnalyze.add(outputTextArray[i].trim().toLowerCase());
                        }
                    }
                }
            }

            // Count the number of occurances of a word in the ArrayList of text from the URL and store the word and its number of occurances in a HashMap.
            HashMap<String, Integer> results = new HashMap<>();
            for (int i = 0; i < textToAnalyze.size(); i++) {
                int wordCount = 0;
                String word = textToAnalyze.get(i);
                for (int j = 0; j < textToAnalyze.size(); j++) {
                    if (textToAnalyze.get(j).equalsIgnoreCase(word)) {
                        wordCount++;
                        textToAnalyze.remove(j);
                    }
                }
                results.put(word, wordCount);
                wordCount = 0;
            }

            // Custom comparator to order the values (occurances) from greatest to least.
            Comparator<Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
                public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                    Integer v1 = e1.getValue();
                    Integer v2 = e2.getValue();
                    String k1 = e1.getKey();
                    String k2 = e2.getKey();
                    // If the number of occurances are equal, sort the words in alphabetical order.
                    if (v2 == v1) {
                        return k1.compareTo(k2);
                    } else {
                        return v2.compareTo(v1);
                    }
                }
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
            }

            // Display the results.
            Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();
            int i = 0;
            for(Entry<String, Integer> mapping : entrySetSortedByValue) {
                i++;
                result += (i + ". " + mapping.getKey() + ", " + mapping.getValue() + "\n");
                // Stop the list at 20 entries.
                if (i == 20) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
    }
}