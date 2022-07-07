/**
 * For testing the methods in the WordOccurrences project.
 * @author Stephen Sturges Jr
 * @version 6/26/2022
 */
package cen3024c;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class JUnitTestLogic {

	/**
	 * Simply prints "Before class." to the console to indicate the start of testing.
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before class.");
	} // End of setUpBeforeClass method.
	
	/**
	 * Prints "Before." to the console to indicate the start of a test.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("Before.");
	} // End of setUp method.
	
	/**
	 * Tests the WordOccurrences.convertArrayListToHashMap() method. Ensures the method returns an output that is not null and prints the result to the console.
	 */
	@Test
	void arrayListToHashMapConversionTest() {
		System.out.println("Test case: convertArrayListToHashMap.");
		ArrayList<String> testList = new ArrayList<String>();
		HashMap<String,Integer> testHashMap = new HashMap<String,Integer>();
		
		testList = WordOccurrences.readText("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm", "The Raven", "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		testHashMap = WordOccurrences.convertArrayListToHashMap(testList);
		assertNotNull(testHashMap);
		System.out.println(testHashMap);
	} // End of arrayListToHashMapConversionTest.
	
	/**
	 * Tests the WordOccurrences.readText() method. Ensures the method returns an output that is not null and prints the result to the console.
	 */
	@Test
	void readTextTest() {
		System.out.println("Test case: readText.");
		ArrayList<String> testList = new ArrayList<String>();
		
		testList = WordOccurrences.readText("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm", "The Raven", "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		assertNotNull(testList);
		System.out.println(testList);
	} // End extractTextTest.
	
	/**
	 * Tests the WordOccurrences.sortHashMap() method. Ensures the method returns an output that is not null and prints the result to the console.
	 */
	@Test
	void sortHashMapTest1() {
		System.out.println("Test case: sortHashMap 1.");
		ArrayList<String> testList = new ArrayList<String>();
		HashMap<String,Integer> testHashMap = new HashMap<String,Integer>();
		HashMap<String, Integer> sortedHashMap;
		
		testList = WordOccurrences.readText("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm", "The Raven", "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		testHashMap = WordOccurrences.convertArrayListToHashMap(testList);
		sortedHashMap = WordOccurrences.sortHashMap(testHashMap);
		assertNotNull(sortedHashMap);
		System.out.println(sortedHashMap);
	} // End of sortHashMapTest1.
	
	/**
	 * Tests the WordOccurrences.sortHashMap() method. Ensures the method returns "the" and "caught" with occurrence counts of 57 and 1 respectively, which is accurate
	 * according to word counts performed in Word.
	 */
	@Test
	void sortHashMapTest2() {
		System.out.println("Test case: sortHashMap 2.");
		ArrayList<String> testList = new ArrayList<String>();
		HashMap<String,Integer> testHashMap = new HashMap<String,Integer>();
		HashMap<String, Integer> sortedHashMap;
		
		testList = WordOccurrences.readText("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm", "The Raven", "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		testHashMap = WordOccurrences.convertArrayListToHashMap(testList);
		sortedHashMap = WordOccurrences.sortHashMap(testHashMap);
		assertTrue(sortedHashMap.get("the") == 57);
		assertTrue(sortedHashMap.get("caught") == 1);
		System.out.println(sortedHashMap);
	} // End of sortHashMapTest2.
	
	/**
	 * Tests the WordOccurrences.convertHashMapToString() method. Ensures the method returns an output that is not null.
	 */
	@Test
	void convertHashMapToStringTest() {
		System.out.println("Test case: convertHashMapToString.");
		ArrayList<String> testList = new ArrayList<String>();
		HashMap<String,Integer> testHashMap = new HashMap<String,Integer>();
		HashMap<String, Integer> sortedHashMap;
		
		testList = WordOccurrences.readText("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm", "The Raven", "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		testHashMap = WordOccurrences.convertArrayListToHashMap(testList);
		sortedHashMap = WordOccurrences.sortHashMap(testHashMap);
		String sortedResultsString = WordOccurrences.convertHashMapToString(sortedHashMap);
		assertNotNull(sortedResultsString);
		System.out.println(sortedResultsString);
	} // End of convertHashMapToStringTest.
	
	/**
	 * Prints "After." to the console to indicate the end of a test.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("After.");
	} // End of tearDown method.
	
	/**
	 * Prints "After class." to the console to indicate the end of testing.
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After class.");
	} // End of tearDownAfterClass method.
	
} // End of JUnitTestLogic Class.
