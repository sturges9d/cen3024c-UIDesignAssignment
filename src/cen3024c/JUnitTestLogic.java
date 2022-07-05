/**
 * @author Stephen Sturges Jr
 * @version 6/26/2022
 */
package cen3024c;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class JUnitTestLogic {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before class.");
	} // End of setUpBeforeClass method.
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Before.");
	} // End of setUp method.
	
	@Test
	void arrayListToHashMapConversionTest() {
		System.out.println("Test case: convertArrayListToHashMap.");
		ArrayList<String> testList = new ArrayList<String>();
		HashMap<String,Integer> testHashMap = new HashMap<String,Integer>();
		
		try {
			testList = TextAnalyzer.extractText(new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm"), "The Raven", "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		} catch (MalformedURLException e) {
			fail("Malformed URL exception.");
			e.printStackTrace();
		} // End of try-catch block.
		testHashMap = TextAnalyzer.convertArrayListToHashMap(testList);
		assertNotNull(testHashMap);
		System.out.println(testHashMap);
	} // End of arrayListToHashMapConversionTest.
	
	@Test
	void extractTextTest() {
		System.out.println("Test case: extractText.");
		ArrayList<String> testList = new ArrayList<String>();
		
		try {
			testList = TextAnalyzer.extractText(new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm"), "The Raven", "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		} catch (MalformedURLException e) {
			fail("Malformed URL exception.");
			e.printStackTrace();
		} // End of try-catch block.
		assertNotNull(testList);
		System.out.println(testList);
	} // End extractTextTest.
	
	@Test
	void sortHashMapTest1() {
		System.out.println("Test case: sortHashMap 1.");
		ArrayList<String> testList = new ArrayList<String>();
		HashMap<String,Integer> testHashMap = new HashMap<String,Integer>();
		HashMap<String, Integer> sortedHashMap;
		
		try {
			testList = TextAnalyzer.extractText(new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm"), "The Raven", "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		} catch (MalformedURLException e) {
			fail("Malformed URL exception.");
			e.printStackTrace();
		} // End of try-catch block.
		testHashMap = TextAnalyzer.convertArrayListToHashMap(testList);
		sortedHashMap = TextAnalyzer.sortHashMap(testHashMap);
		assertNotNull(sortedHashMap);
		System.out.println(sortedHashMap);
	} // End of sortHashMapTest1.
	
	@Test
	void sortHashMapTest2() {
		System.out.println("Test case: sortHashMap 2.");
		ArrayList<String> testList = new ArrayList<String>();
		HashMap<String,Integer> testHashMap = new HashMap<String,Integer>();
		HashMap<String, Integer> sortedHashMap;
		
		try {
			testList = TextAnalyzer.extractText(new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm"), "The Raven", "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		} catch (MalformedURLException e) {
			fail("Malformed URL exception.");
			e.printStackTrace();
		} // End of try-catch block.
		testHashMap = TextAnalyzer.convertArrayListToHashMap(testList);
		sortedHashMap = TextAnalyzer.sortHashMap(testHashMap);
		assertTrue(sortedHashMap.get("the") == 57);
		assertTrue(sortedHashMap.get("caught") == 1);
		System.out.println(sortedHashMap);
	} // End of sortHashMapTest2.
	
	@Test
	void convertHashMapToStringTest() {
		System.out.println("Test case: convertHashMapToString.");
		ArrayList<String> testList = new ArrayList<String>();
		HashMap<String,Integer> testHashMap = new HashMap<String,Integer>();
		HashMap<String, Integer> sortedHashMap;
		
		try {
			testList = TextAnalyzer.extractText(new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm"), "The Raven", "*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
		} catch (MalformedURLException e) {
			fail("Malformed URL exception.");
			e.printStackTrace();
		} // End of try-catch block.
		testHashMap = TextAnalyzer.convertArrayListToHashMap(testList);
		sortedHashMap = TextAnalyzer.sortHashMap(testHashMap);
		String sortedResultsString = TextAnalyzer.convertHashMapToString(sortedHashMap);
		assertNotNull(sortedResultsString);
		System.out.println(sortedResultsString);
	} // End of convertHashMapToStringTest.
	
	@After
	public void tearDown() throws Exception {
		System.out.println("After.");
	} // End of tearDown method.
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After class.");
	} // End of tearDownAfterClass method.
	
} // End of JUnitTestLogic Class.
